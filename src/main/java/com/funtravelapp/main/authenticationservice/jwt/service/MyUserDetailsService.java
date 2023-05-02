package com.funtravelapp.main.authenticationservice.jwt.service;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.jwt.config.MyUserDetails;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User users = repository.findByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("No user found!");
        }
        return new MyUserDetails(users);
    }
}
