package com.funtravelapp.main.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private Integer customerId;
    private Integer sellerId;
    private Integer packageId;
    private BigDecimal price;
    private String customerEmail;
    private String sellerEmail;
}
