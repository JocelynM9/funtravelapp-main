package com.funtravelapp.main.authenticationservice.service.impl;

import com.funtravelapp.main.authenticationservice.dto.UserResponseDTO;
import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.entity.UserToken;
import com.funtravelapp.main.authenticationservice.exception.UserNotFoundException;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.authenticationservice.repository.UserTokenRepository;
import com.funtravelapp.main.authenticationservice.jwt.service.JwtGeneratorInterface;
import com.funtravelapp.main.authenticationservice.service.LoginService;
import com.funtravelapp.main.tokenAuth.validator.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    StringValidator stringValidator;


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

    @Override
    public UserResponseDTO getUser(String authorizationHeader) throws Exception {
        String authHeader = authorizationHeader;
        boolean isValid = stringValidator.setStr(authHeader).isValid();
        System.out.println(authHeader);
        if (!isValid) {
            throw new Exception("Unauthorized, invalid token");
        }

        String token = authHeader.split(" ")[1];
        System.out.println("Token : " + token);

        Optional<UserToken> userToken = userTokenRepository.findByToken(token);

        if(userToken.isEmpty()){
            throw new Exception("Unauthorized, invalid token");
        }

        UserToken ut = userToken.get();

        Optional<User> findUser = userRepository.findById(ut.getUserId());

        if(findUser.isEmpty()){
            throw new Exception("User not found!");
        }

        User u = findUser.get();

        return UserResponseDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .role(u.getRole())
                .build();
    }


}
