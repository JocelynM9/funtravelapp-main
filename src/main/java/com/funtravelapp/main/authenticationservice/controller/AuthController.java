package com.funtravelapp.main.authenticationservice.controller;

import com.funtravelapp.main.authenticationservice.dto.LoginInputDTO;
import com.funtravelapp.main.authenticationservice.dto.RegisterInputDTO;
import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.service.LoginService;
import com.funtravelapp.main.authenticationservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterInputDTO registerInputDTO){
        return registerService.insert(registerInputDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInputDTO loginInputDTO){
        try {
           User user = loginService.getUserByNameAndPassword(loginInputDTO.getUsername(), loginInputDTO.getPassword());
           return loginService.saveUserSession(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
