package com.example.mobi.models;

import java.util.Objects;

public class Product {

    private String name;
    private Double price;
    private String brand;

    public Product() {
        this.name = name;
        this.price = price;
        this.brand = brand;

    }

    public Product(String name, Double price, String brand) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand);
    }
}
