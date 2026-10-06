package com.example.springbasics.order;

import org.springframework.stereotype.Component;

@Component
public class PriceCalculator {

    private static final double UNIT_PRICE = 100.0;

    public double calculate(int quantity) {
        double total = UNIT_PRICE * quantity;
        return quantity >= 5 ? total * 0.9 : total;
    }
}
