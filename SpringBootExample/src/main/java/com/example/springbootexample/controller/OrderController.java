package com.example.springbootexample.controller;

import com.example.springbootexample.model.Availability;
import com.example.springbootexample.model.Order;
import com.example.springbootexample.model.OrderShipment;
import com.example.springbootexample.service.OrderShipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    OrderShipmentServiceImpl orderShipmentService;

    @Autowired
    public OrderController(OrderShipmentServiceImpl orderShipmentService) {
        this.orderShipmentService = orderShipmentService;
    }

    @GetMapping("/getOrderDetails")
    public ResponseEntity<OrderShipment> getOrderDetails(@RequestBody Order order) {

        OrderShipment orderShipment = orderShipmentService.getOrderDetails(order.getOrderId());
        if (orderShipment != null) {
            return new ResponseEntity<>(orderShipment, HttpStatus.OK);
        }
        return new ResponseEntity<>(new OrderShipment(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAvailability")
    public Availability getAvailability(@RequestBody Availability availability) {

        return orderShipmentService.getAvailability(availability.getProductId());
    }

}
