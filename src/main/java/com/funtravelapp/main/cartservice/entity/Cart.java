package com.funtravelapp.main.cartservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "package_id")
    private Integer packageId;
}
