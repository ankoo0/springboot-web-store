package com.project.springbootwebstore.entity.order;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserOrderInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public UserOrderInfo() {
    }

    public UserOrderInfo(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
