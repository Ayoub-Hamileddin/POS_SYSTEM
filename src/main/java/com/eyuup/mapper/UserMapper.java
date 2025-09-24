package com.eyuup.mapper;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.UserDto;

public class UserMapper {
    public static UserDto ToDTO(User savedUser){
        UserDto userDto= new UserDto();
        
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        userDto.setPhone(savedUser.getPhone()); 
        userDto.setRole(savedUser.getRole()); 
        userDto.setLastLoginAt(savedUser.getLastLoginAt()); 
        userDto.setCreatedAt(savedUser.getCreatedAt()); 
        userDto.setUpdatedAt(savedUser.getUpdatedAt()); 
        return userDto;
    }
}
