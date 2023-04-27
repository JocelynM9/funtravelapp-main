package com.funtravelapp.main.cartservice.controller;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/new")
    public ResponseEntity<?> newCartData(@RequestBody NewCartDTO cart){
        return cartService.save(cart);
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


}
