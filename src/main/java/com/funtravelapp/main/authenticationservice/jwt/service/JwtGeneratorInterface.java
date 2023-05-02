package com.funtravelapp.main.authenticationservice.jwt.service;

import com.funtravelapp.main.authenticationservice.entity.User;

public interface JwtGeneratorInterface {
    String generateToken(User user);
}
