package com.eyuup.payload.dto;

import java.time.LocalDateTime;

import com.eyuup.domain.UserRole;


public class EmployeeDto {
    private Long id;

    private BranchDTO branchDTO;

    private Long BranchId;

    private UserRole position;

    private Double salary;

    private  Boolean status ;

    private UserDto userDto;
    
    private  LocalDateTime createdAt ;

    private  LocalDateTime updatedAt ;
    
}
