package com.project.springbootwebstore.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonProduct {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("quantity")
    private Long quantity;

    public JsonProduct(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public JsonProduct() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "JsonProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
