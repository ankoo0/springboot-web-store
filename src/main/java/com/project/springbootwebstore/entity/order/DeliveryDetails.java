package com.project.springbootwebstore.entity.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDetails {

    private String city;
    private String street;
    private String building;
    private String apartmentNo;
    private LocalDate deliveryDate;

}