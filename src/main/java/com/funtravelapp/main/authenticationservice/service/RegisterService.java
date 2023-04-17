package com.funtravelapp.main.authenticationservice.service;

import com.funtravelapp.main.authenticationservice.dto.RegisterInputDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    public ResponseEntity<String> insertNewCustomer(RegisterInputDTO registerInputDTO);
    public ResponseEntity<String> insertNewSeller(RegisterInputDTO registerInputDTO);
}
