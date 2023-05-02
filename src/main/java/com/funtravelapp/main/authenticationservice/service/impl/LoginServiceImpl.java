package com.funtravelapp.main.authenticationservice.service.impl;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.entity.UserToken;
import com.funtravelapp.main.authenticationservice.exception.UserNotFoundException;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.authenticationservice.repository.UserTokenRepository;
import com.funtravelapp.main.authenticationservice.jwt.service.JwtGeneratorInterface;
import com.funtravelapp.main.authenticationservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserToken saveUserSession(User mUser) throws Exception {

        User user = userRepository.findByUsernameAndPassword(mUser.getUsername(), mUser.getPassword());


        if(userTokenRepository.existsByUserId(user.getId())){
            throw new Exception("Already Logged in!");

        }

        String setToken = jwtGenerator.generateToken(user);

        UserToken userToken = new UserToken();
        userToken.setTokenId(0);
        userToken.setUserId(user.getId());
        userToken.setToken(setToken);
        userToken.setExpiredTime(LocalDateTime.now().plusMinutes(10));
        userToken.setExpired(false);

        return userTokenRepository.save(userToken);

    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(name, password);
        if(user == null){
            throw new UserNotFoundException("Invalid username or password");
        }
        return user;
    }

    @Override
    public String logout(Integer id) {
        UserToken token = userTokenRepository.findUserTokenByUserId(id);
        userTokenRepository.delete(token);
        return "Logged out!";
    }


}
