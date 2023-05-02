package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;
import org.springframework.http.ResponseEntity;

public interface CartService {
    Cart save (NewCartDTO cart) throws Exception;
    ResponseEntity<?> findAllByCustomerId(Integer customerId);
    ResponseEntity<?> delete(Integer customerId, Integer packageId);

}
