package com.funtravelapp.main.packageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackageInputDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String image;

    @JsonProperty("price")
    private BigDecimal price;
}
