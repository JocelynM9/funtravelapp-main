package com.funtravelapp.main.authenticationservice.controller;

import com.funtravelapp.main.authenticationservice.dto.LoginInputDTO;
import com.funtravelapp.main.authenticationservice.dto.RegisterInputDTO;
import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.service.LoginService;
import com.funtravelapp.main.authenticationservice.service.RegisterService;
import com.funtravelapp.main.responseMapper.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterInputDTO registerInputDTO){
        try{
            return ResponseMapper.ok(null, registerService.insert(registerInputDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInputDTO loginInputDTO){
        try {
           User user = loginService.getUserByNameAndPassword(loginInputDTO.getUsername(), loginInputDTO.getPassword());
           return ResponseMapper.ok(null, loginService.saveUserSession(user));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable("id") Integer userId){
        try {
           return ResponseMapper.ok(null, loginService.logout(userId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        try{
            return ResponseMapper.ok(null, loginService.getUser(authorizationHeader));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
