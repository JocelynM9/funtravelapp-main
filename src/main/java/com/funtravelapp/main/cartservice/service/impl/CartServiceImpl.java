package com.funtravelapp.main.cartservice.service.impl;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.cartservice.dto.NewCartDTO;
import com.funtravelapp.main.cartservice.entity.Cart;
import com.funtravelapp.main.cartservice.repository.CartRepository;
import com.funtravelapp.main.cartservice.service.CartService;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    PackageRepository packageRepository;

    @Override
    @Transactional
    public Cart save(String authorizationHeader, Map<String, Boolean> role, User user, NewCartDTO cart) throws Exception{
        if(user.getRole().equalsIgnoreCase("seller")){
            throw new Exception("Access denied!");
        }

        Optional<Package> findPackage = packageRepository.findById(cart.getPackageId());

        if(findPackage.isEmpty()){
            throw new Exception("Package doesn't exist");
        }

        Package aPackage = findPackage.get();

        Cart aCart = new Cart();

        aCart.setCartId(0);
        aCart.setCustomerId(user.getId());
        aCart.setSellerId(aPackage.getUserId());
        aCart.setPackageId(cart.getPackageId());
        return cartRepository.save(aCart);
    }

    @Override
    public List<Cart> findAllByCustomerId(String authorizationHeader, Map<String, Boolean> role, User user) throws Exception {
        if(user.getRole().equalsIgnoreCase("seller")){
            throw new Exception("Access denied!");
        }

        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public void delete(String authorizationHeader, Map<String, Boolean> role, User user, Integer cartId) throws Exception {

        if(user.getRole().equalsIgnoreCase("seller")){
            throw new Exception("Access denied!");
        }
        Optional<Cart> getCart = cartRepository.findById(cartId);

        if(getCart.isEmpty()){
            throw new Exception("Not found!");
        }

        Cart cart = getCart.get();

        if(!cart.getCustomerId().equals(user.getId())){
            throw new Exception("Access denied!");
        }

        cartRepository.delete(cart);

    }
}
