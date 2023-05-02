package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.cartservice.dto.CreateOrder;
import com.funtravelapp.main.cartservice.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private final KafkaTemplate<String, CreateOrder> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, CreateOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(CreateOrder dto) throws Exception {
        if(dto.getData().isEmpty()){
            throw new Exception("No Data!");
        }

        kafkaTemplate.send("CreateOrder", dto);
    }
}
