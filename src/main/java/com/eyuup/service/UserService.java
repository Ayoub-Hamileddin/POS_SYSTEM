package com.eyuup.service;

import java.util.List;

import com.eyuup.exceptions.UserException;
import com.eyuup.modal.User;

public interface UserService {


    User getUserFromJwtToken(String token) throws UserException;

    User getCurrentUser() throws UserException;

    User getUserByEmail(String email) throws UserException;

    User getUserById(Long id) throws UserException;

    List<User>getAllUsers();

}
