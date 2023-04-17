package com.funtravelapp.main.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginInputDTO {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}
