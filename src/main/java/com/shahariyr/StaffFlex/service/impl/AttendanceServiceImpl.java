package com.shahariyr.StaffFlex.service.impl;

import com.shahariyr.StaffFlex.dto.AttendanceDto;
import com.shahariyr.StaffFlex.entity.Attendance;
import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.exception.ResourceNotFoundException;
import com.shahariyr.StaffFlex.repository.AttendanceRepository;
import com.shahariyr.StaffFlex.repository.EmployeeRepository;
import com.shahariyr.StaffFlex.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                EmployeeRepository employeeRepository,
                                ModelMapper modelMapper) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AttendanceDto punchIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", employeeId));

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.now());
        attendance.setPunchIn(LocalTime.now());
        attendance.setStatus("Present");

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return modelMapper.map(savedAttendance, AttendanceDto.class);
    }

    @Override
    public AttendanceDto punchOut(Long employeeId) {
        // Logic to find today's attendance for the employee and update punchOut
        // Simplified for demonstration
        List<Attendance> attendances = attendanceRepository.findByEmployee(
                employeeRepository.findById(employeeId).get()
        );
        Attendance todayAttendance = attendances.stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Attendance record not found for today"));

        todayAttendance.setPunchOut(LocalTime.now());
        Attendance updatedAttendance = attendanceRepository.save(todayAttendance);
        return modelMapper.map(updatedAttendance, AttendanceDto.class);
    }

    @Override
    public List<AttendanceDto> getEmployeeAttendance(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", employeeId));
        List<Attendance> attendances = attendanceRepository.findByEmployee(employee);
        return attendances.stream().map(a -> modelMapper.map(a, AttendanceDto.class))
                .collect(Collectors.toList());
    }
}
