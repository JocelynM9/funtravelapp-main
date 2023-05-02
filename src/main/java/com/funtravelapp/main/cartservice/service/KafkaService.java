package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.cartservice.dto.CreateOrder;

public interface KafkaService {

    public void sendMessage(CreateOrder dto) throws Exception;
}
