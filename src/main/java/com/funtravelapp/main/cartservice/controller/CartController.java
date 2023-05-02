package com.funtravelapp.main.cartservice.controller;

import com.funtravelapp.main.cartservice.dto.CreateOrder;
import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.service.CartService;
import com.funtravelapp.main.cartservice.service.KafkaService;
import com.funtravelapp.main.responseMapper.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/new")
    public ResponseEntity<?> newCartData(@RequestBody NewCartDTO cart){
        try{
            return ResponseMapper.ok(null, cartService.save(cart));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @GetMapping("/all/{customerId}")
    public ResponseEntity<?> allByCustomerId(@PathVariable("customerId") int customerId){
        return cartService.findAllByCustomerId(customerId);
    }

    @DeleteMapping("/delete/{customerId}/{packageId}")
    public ResponseEntity<?> deleteCartData(@PathVariable("customerId") int customerId,
                                            @PathVariable("packageId") int packageId){
        return cartService.delete(customerId, packageId);

    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder createOrder){
        try{
            kafkaService.sendMessage(createOrder);
            return ResponseMapper.ok(null, "Message sent!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }


    }


}
