package com.funtravelapp.main.cartservice.controller;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.service.CartService;
import com.funtravelapp.main.cartservice.service.KafkaService;
import com.funtravelapp.main.responseMapper.ResponseMapper;
import com.funtravelapp.main.tokenAuth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/new")
    public ResponseEntity<?> newCartData(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                         @RequestBody NewCartDTO cart){
        try{
            return ResponseMapper.ok(null, cartService.save(authorizationHeader,
                    this.roleService.getCustomerAndSeller(),
                    null,
                    cart));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> allByCustomerId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        try{
            return ResponseMapper.ok(null, cartService.findAllByCustomerId(
                    authorizationHeader,
                    this.roleService.getCustomerAndSeller(),
                    null
            ));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }

    }

    @DeleteMapping("/delete/{packageId}")
    public ResponseEntity<?> deleteCartData(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                            @PathVariable("packageId") int packageId){

        try{
            cartService.delete(authorizationHeader,
                    this.roleService.getCustomerAndSeller(),
                    null, packageId);
            return ResponseMapper.ok(null, null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }

    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        try{

            return ResponseMapper.ok(null, kafkaService.sendMessage(authorizationHeader,
                    this.roleService.getCustomer(),
                    null));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }


    }


}
