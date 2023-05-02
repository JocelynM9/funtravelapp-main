package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.repository.CartRepository;
import com.funtravelapp.main.cartservice.service.CartService;
import com.funtravelapp.main.cartservice.service.KafkaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;


    @Override
    @Transactional
    public Cart save(NewCartDTO cart) throws Exception{
        Cart aCart = new Cart();

        if(cart.getCustomerId().equals(0) || cart.getSellerId().equals(0)
        || cart.getPackageId().equals(0)){
            throw new Exception("400");
        }

        aCart.setCartId(0);
        aCart.setCustomerId(cart.getCustomerId());
        aCart.setSellerId(cart.getSellerId());
        aCart.setPackageId(cart.getPackageId());
        return cartRepository.save(aCart);
    }

    @Override
    public ResponseEntity<?> findAllByCustomerId(Integer customerId) {
        return new ResponseEntity<>(cartRepository.findCartByCustomerId(customerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Integer customerId, Integer packageId) {
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
