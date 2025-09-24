package com.eyuup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.exceptions.UserException;
import com.eyuup.payload.dto.UserDto;
import com.eyuup.payload.response.AuthResponse;
import com.eyuup.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    // * dependecy injection
    private final AuthService authService;


    // this is fot register
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException{

        return  ResponseEntity.ok(
                authService.signup(userDto)
        ) ;


    }

        // this is fot login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException{

        return  ResponseEntity.ok(
                authService.login(userDto)
        ) ;


    }
}
