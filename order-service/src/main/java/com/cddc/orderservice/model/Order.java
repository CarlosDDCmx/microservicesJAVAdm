package com.cddc.orderservice.model;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    private Long ID;
    private String productId;
    private int quantity;

    public Long getProductId() {
        return this.ID;
    }
    // Getters and setters
}