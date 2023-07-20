package com.example.springbootexample.service;

import com.example.springbootexample.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderShipmentServiceImpl implements OrderShipmentService {
    private final List<Order> orders = new ArrayList<>();
    private final List<Shipment> shipments = new ArrayList<>();

    private final List<Supply> supplys = new ArrayList<>();
    private final List<Demand> demands = new ArrayList<>();
    private final List<Availability> availabilities = new ArrayList<>();

    @Override
    public OrderShipment getOrderDetails(String orderId) {
        createRecords();
        OrderShipment orderShipment = new OrderShipment();
        Order order = orders.stream()
                .filter(order1 -> order1.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        Shipment shipment = shipments.stream()
                .filter(s -> s.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
        if (order != null) {
            orderShipment.setOrders(order);
        }
        if (shipment != null) {
            orderShipment.setShipments(shipment);
        }
        return orderShipment;
    }

    @Override
    public Availability getAvailability(String productId) {
        createSupplyDemand();
        Supply supply = supplys.stream().filter(supply1 -> supply1.getProductId().equals(productId)).findFirst().get();
        Demand demand = demands.stream().filter(demand1 -> demand1.getProductId().equals(productId)).findFirst().get();

        return null;
    }

    private void createSupplyDemand() {
        for (int i = 1; i <= 5; i++) {
            Demand demand = new Demand();
            demand.setProductId("Product" + i);
            demand.setQuantity(new Double(i + 2));
            Supply supply = new Supply();
            supply.setProductId(demand.getProductId());
            supply.setProductId("product" + 1);
            demands.add(demand);
            supplys.add(supply);
            Availability availability = new Availability();
            availability.setAvailability(10);
            availability.setProductId(demand.getProductId());
            availabilities.add(availability);
        }

    }

    private void createRecords() {
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setOrderId("Order" + i);
            order.setProductId("Product " + i);
            order.setQty("Qty " + i);
            orders.add(order);
            Shipment shipment = new Shipment();
            shipment.setOrderId(order.getOrderId());
            shipment.setShipmentId("Shipment " + i);
            shipment.setProductId("Product " + i);
            shipment.setShipmentDate(LocalDate.now().plusDays(i));
            shipment.setQty(order.getQty());
            shipments.add(shipment);
        }

    }
}
