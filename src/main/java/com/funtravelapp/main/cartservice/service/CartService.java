package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    Cart save (NewCartDTO cart) throws Exception;
    List<Cart> findAllByCustomerId(Integer customerId);
    void delete(Integer customerId, Integer packageId) throws Exception;

}
