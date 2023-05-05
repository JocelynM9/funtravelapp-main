package com.funtravelapp.main.cartservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {

    private List<OrderData> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class OrderData{
        private Integer customerId;
        private Integer sellerId;
        private Integer packageId;
        private BigDecimal price;
        private String customerEmail;
        private String sellerEmail;
    }


}
