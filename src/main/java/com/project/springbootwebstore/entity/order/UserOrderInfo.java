package com.project.springbootwebstore.entity.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
