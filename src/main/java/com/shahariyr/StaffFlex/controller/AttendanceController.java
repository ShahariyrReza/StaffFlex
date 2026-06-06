package com.shahariyr.StaffFlex.controller;

import com.shahariyr.StaffFlex.dto.AttendanceDto;
import com.shahariyr.StaffFlex.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/punch-in/{employeeId}")
    public ResponseEntity<AttendanceDto> punchIn(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.punchIn(employeeId));
    }

    @PostMapping("/punch-out/{employeeId}")
    public ResponseEntity<AttendanceDto> punchOut(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.punchOut(employeeId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<AttendanceDto> getEmployeeAttendance(@PathVariable Long employeeId) {
        return attendanceService.getEmployeeAttendance(employeeId);
    }
}
