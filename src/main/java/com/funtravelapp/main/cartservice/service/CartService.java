package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> save (NewCartDTO cart);
    ResponseEntity<?> findAllByCustomerId(int customerId);
    ResponseEntity<?> delete(int customerId, int packageId);
}
