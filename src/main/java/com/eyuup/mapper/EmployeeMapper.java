package com.eyuup.mapper;

import com.eyuup.modal.Employee;
import com.eyuup.payload.dto.EmployeeDto;

public class EmployeeMapper {
    public static EmployeeDto toDTO(Employee employee){
        return EmployeeDto.builder()
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .position(employee.getPosition())
                .password(employee.getPassword())
                .user(UserMapper.ToDTO(employee.getUser()))
                .branch(BranchMapper.toDTO(employee.getBranch()))
                .salary(employee.getSalary())
                .status(employee.getStatus())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
        .build();
    }
}
