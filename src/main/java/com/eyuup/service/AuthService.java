package com.eyuup.service;

import com.eyuup.exceptions.UserException;
import com.eyuup.payload.dto.UserDto;
import com.eyuup.payload.response.AuthResponse;

public interface AuthService {
    
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
 
