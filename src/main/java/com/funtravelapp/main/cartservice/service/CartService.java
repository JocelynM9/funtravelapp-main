package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.cartservice.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    void save (Cart cart);
    List<Cart> findAllByCustomerId(int customerId);
    ResponseEntity<?> delete(int customerId, int packageId);
}
