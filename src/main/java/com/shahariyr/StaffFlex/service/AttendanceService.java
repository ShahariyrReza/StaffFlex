package com.shahariyr.StaffFlex.service;

import com.shahariyr.StaffFlex.dto.AttendanceDto;
import java.util.List;

public interface AttendanceService {
    AttendanceDto punchIn(Long employeeId);
    AttendanceDto punchOut(Long employeeId);
    List<AttendanceDto> getEmployeeAttendance(Long employeeId);
}
