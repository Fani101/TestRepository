package com.example.springbootexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    private String orderId;
    private String shipmentId;
    private String productId;
    private LocalDate shipmentDate;
    private String qty;
}
