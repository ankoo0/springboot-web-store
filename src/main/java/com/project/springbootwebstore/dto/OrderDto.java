package com.project.springbootwebstore.dto;

import java.util.List;

public record OrderDto(
        String firstName,
        String lastName,
        String email,
        String phone,
        String city,
        String street,
        String building,
        String apartmentNo,
        String date,
        List<CartItem> orderedProducts) {

}
