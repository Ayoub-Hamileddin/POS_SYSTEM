package com.eyuup.payload.dto;

import java.time.LocalDateTime;

import com.eyuup.domain.UserRole;

import lombok.Data;


@Data
public class UserDto {
  
    private Long id;

    
    private String fullName;

    
    private String email;
    
    
    private String password;

    
    private UserRole role;

    private String phone ;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLoginAt;
}
