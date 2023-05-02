package com.funtravelapp.main.authenticationservice.service;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.entity.UserToken;
import com.funtravelapp.main.authenticationservice.exception.UserNotFoundException;

public interface LoginService {
    public UserToken saveUserSession(User mUser) throws Exception;
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;
    public String logout(Integer id);
}
