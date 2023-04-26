package com.funtravelapp.main.authenticationservice.service.impl;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.entity.UserToken;
import com.funtravelapp.main.authenticationservice.exception.UserNotFoundException;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.authenticationservice.repository.UserTokenRepository;
import com.funtravelapp.main.authenticationservice.config.JwtGeneratorInterface;
import com.funtravelapp.main.authenticationservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtGeneratorInterface jwtGenerator;


    @Override
    public ResponseEntity<?> saveUserSession(User mUser) throws UserNotFoundException {

        User user = userRepository.findByUsername(mUser.getUsername());
        if(mUser == null){
            throw new UserNotFoundException("UserName or Password is Invalid");
        }

        String setToken = jwtGenerator.generateToken(mUser);

        UserToken userToken = new UserToken();
        userToken.setTokenId(0);
        userToken.setUserId(user.getId());
        userToken.setToken(setToken);
        userToken.setExpiredTime(LocalDateTime.now().plusMinutes(10));
        userToken.setExpired(false);
        userTokenRepository.save(userToken);

        return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(name, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }


}
