package com.funtravelapp.main.authenticationservice.config;

import com.funtravelapp.main.authenticationservice.entity.User;

import java.util.Map;

public interface JwtGeneratorInterface {
    String generateToken(User user);
}
