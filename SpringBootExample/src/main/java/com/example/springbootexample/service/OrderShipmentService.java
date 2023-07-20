package com.example.springbootexample.service;

import com.example.springbootexample.model.Availability;
import com.example.springbootexample.model.OrderShipment;

public interface OrderShipmentService {
    OrderShipment getOrderDetails(String orderId);

    Availability getAvailability(String productId);
}
