package com.eyuup.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.eyuup.configuration.JwtProvider;
import com.eyuup.exceptions.UserException;
import com.eyuup.modal.User;
import com.eyuup.repository.UserRepository;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public User getUserFromJwtToken(String token) throws UserException {

       String email=jwtProvider.getEmailFromJwtToken(token);
       User user=userRepository.findByEmail(email);
      
       if (user==null) {
        throw new UserException("user not found ");
       }

       return user;

    }

    @Override
    public User getCurrentUser() throws UserException {
        // getting the current user from the security context holder
        String email=SecurityContextHolder.getContext().getAuthentication().getName();

        User user =userRepository.findByEmail(email);

        if (user==null) {

                throw new UserException("user not found ");
            }

         return user;

      
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
      User user =userRepository.findByEmail(email);
       if (user==null) {
            throw new UserException("user not found ");
       }

       return user;
    }


    @Override
    public User getUserById(Long id) throws UserException {
        User user =userRepository.findById(id)
        .orElseThrow(()-> new UserException("user not found "));
        return user;
    }


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

}
