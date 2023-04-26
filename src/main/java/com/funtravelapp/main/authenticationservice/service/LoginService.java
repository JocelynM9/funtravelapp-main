package com.funtravelapp.main.authenticationservice.service;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public ResponseEntity<?> saveUserSession(User mUser) throws UserNotFoundException;
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
}
