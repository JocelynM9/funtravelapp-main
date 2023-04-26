package com.funtravelapp.main.cartservice.controller;

import com.funtravelapp.main.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @DeleteMapping("/delete/{customerId}/{packageId}")
    public ResponseEntity<?> deleteCartData(@PathVariable("customerId") int customerId,
                                            @PathVariable("packageId") int packageId){
        return cartService.delete(customerId, packageId);

    }


}
