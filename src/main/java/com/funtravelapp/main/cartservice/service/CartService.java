package com.funtravelapp.main.cartservice.service;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    Cart save (String authorizationHeader, Map<String, Boolean> role, User user, NewCartDTO cart) throws Exception;
    List<Cart> findAllByCustomerId(String authorizationHeader, Map<String, Boolean> role, User user) throws Exception;
    void delete(String authorizationHeader, Map<String, Boolean> role, User user, Integer cartId) throws Exception;

}
