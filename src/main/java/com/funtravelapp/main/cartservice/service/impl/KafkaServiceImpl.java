package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.cartservice.dto.CreateOrder;
import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.repository.CartRepository;
import com.funtravelapp.main.cartservice.service.KafkaService;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private final KafkaTemplate<String, CreateOrder> kafkaTemplate;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PackageRepository packageRepository;

    public KafkaServiceImpl(KafkaTemplate<String, CreateOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String sendMessage(String authorizationHeader, Map<String, Boolean> role , User user) throws Exception {

        List<Cart> cartList = cartRepository.findByCustomerId(user.getId());

        if(cartList.isEmpty()){
            throw new Exception("No data!");
        }

        List<CreateOrder.OrderData> odList = new ArrayList<>();

        for (Cart eCart:
             cartList) {
            Optional<Package> findPackage = packageRepository.findById(eCart.getPackageId());

            if(findPackage.isEmpty()){
                throw new Exception("Package not found!");
            }

            Package aPackage = findPackage.get();

            Optional<User> getSeller = userRepository.findById(aPackage.getUserId());
            if(getSeller.isEmpty()){
                throw new Exception("Seller not found!");
            }

            User seller = getSeller.get();

            CreateOrder.OrderData orderData= CreateOrder.OrderData.builder()
                    .customerId(user.getId())
                    .sellerId(eCart.getSellerId())
                    .packageId(eCart.getPackageId())
                    .price(aPackage.getPrice())
                    .customerEmail(user.getEmail())
                    .sellerEmail(seller.getEmail())
                    .build()
                    ;

            odList.add(orderData);

        }

        CreateOrder order = CreateOrder.builder()
                .data(odList)
                .build();


        kafkaTemplate.send("CreateOrder", order);

        return "Order created!";
    }
}
