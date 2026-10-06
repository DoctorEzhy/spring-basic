package com.example.springbasics.order;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();
    private final AtomicInteger idSequence = new AtomicInteger(1);

    public Order save(String product, int quantity, double total) {
        Order order = new Order(idSequence.getAndIncrement(), product, quantity, total);
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return List.copyOf(orders);
    }
}
