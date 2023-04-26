package com.funtravelapp.main.cartservice.dto;

import lombok.Data;

@Data
public class CreateOrder {
    private int customerId;

    private int sellerId;

    private int packageId;
}
