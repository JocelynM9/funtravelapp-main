package com.funtravelapp.main.authenticationservice.controller;

import com.funtravelapp.main.authenticationservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    LoginService loginService;

}
