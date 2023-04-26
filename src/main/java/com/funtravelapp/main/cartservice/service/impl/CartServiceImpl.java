package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.repository.CartRepository;
import com.funtravelapp.main.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void save(Cart cart) {

    }

    @Override
    public List<Cart> findAllByCustomerId(int customerId) {
        return cartRepository.findCartByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<?> delete(int customerId, int packageId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setPackageId(packageId);

        if(cartRepository.existsByCustomerIdAndPackageId(cart.getCustomerId(), cart.getPackageId()) == null){
            return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        }else{
            cartRepository.delete(cart);
            return new ResponseEntity<>("Deleted!", HttpStatus.OK);
        }

    }
}
