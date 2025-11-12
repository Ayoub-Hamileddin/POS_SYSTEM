package com.eyuup.payload.dto;

import java.time.LocalDateTime;

import com.eyuup.domain.UserRole;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmployeeDto {
    private Long id;

     private String fullName;

    private String email;
    
    private String password;
    
    private String phone;

    private UserRole position;

    private BranchDTO branch;

    private Long BranchId;

    private Double salary;

    private  Boolean status ;

    private UserDto user;
    
    private  LocalDateTime createdAt ;

    private  LocalDateTime updatedAt ;
    
}
