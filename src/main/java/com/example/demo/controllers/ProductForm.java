package com.example.demo.controllers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductForm {

    @NotEmpty(message = "The product name is required")
    private String name;

    @NotNull(message = "The price is required")
    @Min(value = 1, message = "The price must be greater than 0")
    private Double price;

    private String img;
    private String description; // Descripción del producto

    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }}
