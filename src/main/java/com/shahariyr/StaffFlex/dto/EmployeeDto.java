package com.shahariyr.StaffFlex.dto;

import com.shahariyr.StaffFlex.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private LocalDate joiningDate;
    private EmployeeStatus status;
    private Long departmentId;
    private Long designationId;
}
