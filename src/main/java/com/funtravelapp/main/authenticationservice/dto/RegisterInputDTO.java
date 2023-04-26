package com.funtravelapp.main.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterInputDTO {

    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "confirmPassword")
    private String confirmPassword;
    @JsonProperty(value = "role")
    private String role;

}
