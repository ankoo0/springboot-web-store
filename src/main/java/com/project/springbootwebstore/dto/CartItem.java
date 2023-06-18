package com.project.springbootwebstore.dto;

public final class CartItem {
    private final int id;
    private final int quantity;

    public CartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}