package com.example.mobi.models;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private Double price;
    private String brand;

    public Item() {
    }

    public Item(String name, Double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
