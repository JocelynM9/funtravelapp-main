package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.authenticationservice.entity.User;

import java.util.Map;

public interface KafkaService {

    public String sendMessage(String authorizationHeader, Map<String, Boolean> role , User user) throws Exception;
}
