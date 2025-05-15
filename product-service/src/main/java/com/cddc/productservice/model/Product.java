package com.cddc.productservice.model;

import jakarta.persistence.*;

// ID, name, description, price, and stock.
@Entity
public class Product {
    @Id
    private Long ID;
    private String name;
    private String description;
    private double price;
    private int stock;
    // Getters and setters
}