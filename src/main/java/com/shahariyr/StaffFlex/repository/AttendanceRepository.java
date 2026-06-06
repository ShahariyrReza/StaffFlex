package com.shahariyr.StaffFlex.repository;

import com.shahariyr.StaffFlex.entity.Attendance;
import com.shahariyr.StaffFlex.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployee(Employee employee);
    List<Attendance> findByDate(LocalDate date);
}
