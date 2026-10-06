package com.example.springbasics.order;

import com.example.springbasics.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    private PriceCalculator priceCalculator;

    @Autowired
    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        System.out.println("[OrderService] 1) вызван КОНСТРУКТОР (priceCalculator пока = "
                + priceCalculator + ")");
    }

    @Autowired
    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
        System.out.println("[OrderService] 2) вызван СЕТТЕР (priceCalculator внедрён)");
    }

    public Order placeOrder(String product, int quantity) {
        double total = priceCalculator.calculate(quantity);
        Order order = orderRepository.save(product, quantity, total);
        notificationService.notifyOrderCreated(order);
        return order;
    }

    public void alertUrgent(String message) {
        notificationService.notifyUrgent(message);
    }
}
