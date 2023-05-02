package com.funtravelapp.main.authenticationservice.service.impl;

import com.funtravelapp.main.authenticationservice.dto.RegisterInputDTO;
import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.authenticationservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserRepository userRepository;

//    @Override
//    public ResponseEntity<String> insertNewCustomer(RegisterInputDTO registerInputDTO) {
//        if(registerInputDTO.getUsername().isBlank()
//                || registerInputDTO.getEmail().isBlank()
//                || registerInputDTO.getPassword().isBlank()
//                || registerInputDTO.getConfirmPassword().isBlank()
//        )
//        {
//            return new ResponseEntity<String>("Please fill empty blanks!", HttpStatus.BAD_REQUEST);
//        }
//        //check if username already exists in database
//        if(userRepository.existsByUsername(registerInputDTO.getUsername())){
//            return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
//        }
//        //check if email already exists in database
//        if(userRepository.existsByEmail(registerInputDTO.getEmail())){
//            return new ResponseEntity<String>("Email is already taken!", HttpStatus.BAD_REQUEST);
//        }
//        //check if password and confirmPassword don't match
//        if(!registerInputDTO.getPassword().equals(registerInputDTO.getConfirmPassword())){
//            return new ResponseEntity<String>("Password doesn't match", HttpStatus.BAD_REQUEST);
//        }
//
//        //create new user customer
//        User user = new User();
//        user.setId(0);
//        user.setUsername(registerInputDTO.getUsername());
//        user.setEmail(registerInputDTO.getEmail());
//        user.setPassword(registerInputDTO.getPassword());
//        user.setRole("Customer");
//        userRepository.save(user);
//
//        return new ResponseEntity<String>("Sucessfully created new customer account!", HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<String> insertNewSeller(RegisterInputDTO registerInputDTO) {
//        if(registerInputDTO.getUsername().isBlank()
//                || registerInputDTO.getEmail().isBlank()
//                || registerInputDTO.getPassword().isBlank()
//                || registerInputDTO.getConfirmPassword().isBlank()
//        )
//        {
//            return new ResponseEntity<String>("Please fill empty blanks!", HttpStatus.BAD_REQUEST);
//        }
//        //check if username already exists in database
//        if(userRepository.existsByUsername(registerInputDTO.getUsername())){
//            return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
//        }
//        //check if email already exists in database
//        if(userRepository.existsByEmail(registerInputDTO.getEmail())){
//            return new ResponseEntity<String>("Email is already taken!", HttpStatus.BAD_REQUEST);
//        }
//        //check if password and confirmPassword don't match
//        if(!registerInputDTO.getPassword().equals(registerInputDTO.getConfirmPassword())){
//            return new ResponseEntity<String>("Password doesn't match", HttpStatus.BAD_REQUEST);
//        }
//
//        //create new user seller
//        User user = new User();
//        user.setId(0);
//        user.setUsername(registerInputDTO.getUsername());
//        user.setEmail(registerInputDTO.getEmail());
//        user.setPassword(registerInputDTO.getPassword());
//        user.setRole("Seller");
//        userRepository.save(user);
//
//        return new ResponseEntity<>("Sucessfully created new seller account!", HttpStatus.OK);
//    }

    @Override
    public User insert(RegisterInputDTO registerInputDTO) throws Exception{

        if(registerInputDTO.getUsername().isBlank()
                || registerInputDTO.getEmail().isBlank()
                || registerInputDTO.getPassword().isBlank()
                || registerInputDTO.getConfirmPassword().isBlank()
                || registerInputDTO.getRole().isBlank()
        )
        {
            throw new Exception("Please fill empty blanks!");
        }
        //check if username already exists in database
        if(userRepository.existsByUsername(registerInputDTO.getUsername())){
            throw new Exception("Username is already taken!");
        }
        //check if email already exists in database
        if(userRepository.existsByEmail(registerInputDTO.getEmail())){
            throw new Exception("Email is already taken!");
        }
        //check if password and confirmPassword don't match
        if(!registerInputDTO.getPassword().equals(registerInputDTO.getConfirmPassword())){
            throw new Exception("Password doesn't match");
        }

        //create new user customer
        User user = new User();
        user.setId(0);
        user.setUsername(registerInputDTO.getUsername());
        user.setEmail(registerInputDTO.getEmail());
        user.setPassword(registerInputDTO.getPassword());
        user.setRole(registerInputDTO.getRole());


        return userRepository.save(user);
    }


}
