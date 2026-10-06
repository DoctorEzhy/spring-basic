package com.example.springbasics.order;

public record Order(int id, String product, int quantity, double total) {
}
