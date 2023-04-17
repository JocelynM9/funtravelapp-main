package com.funtravelapp.main.authenticationservice.controller;

import com.funtravelapp.main.authenticationservice.dto.RegisterInputDTO;
import com.funtravelapp.main.authenticationservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("customer")
    public ResponseEntity<String> insertNewCustomer(@RequestBody RegisterInputDTO registerInputDTO){
        return registerService.insertNewCustomer(registerInputDTO);
    }

    @PostMapping("seller")
    public ResponseEntity<String> insertNewSeller(@RequestBody RegisterInputDTO registerInputDTO){
        return registerService.insertNewSeller(registerInputDTO);
    }

}
