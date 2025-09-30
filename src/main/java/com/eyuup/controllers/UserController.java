package com.eyuup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.exceptions.UserException;
import com.eyuup.mapper.UserMapper;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.UserDto;
import com.eyuup.service.UserService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException
    {
        User user=userService.getUserFromJwtToken(jwt);

        if (user==null) {
            throw new UserException("user not found");
        }
        return ResponseEntity.ok(UserMapper.ToDTO(user));
        
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws UserException ,Exception
    {
        User user=userService.getUserById(id);

        if (user==null) {
            throw new UserException("user not found");
        }

        return ResponseEntity.ok(UserMapper.ToDTO(user));
        
    }


}
