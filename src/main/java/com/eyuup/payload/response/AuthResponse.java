package com.eyuup.payload.response;

import com.eyuup.payload.dto.UserDto;

import lombok.Data;



@Data
public class AuthResponse 
{
    private String jwt;

    private String message;

    private UserDto user;
    


}
