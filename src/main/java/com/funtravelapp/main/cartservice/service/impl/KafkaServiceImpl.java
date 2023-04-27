package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.service.KafkaService;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaServiceImpl implements KafkaService {
    private KafkaTemplate<String, Cart> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, Cart> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage() {

    }
}
