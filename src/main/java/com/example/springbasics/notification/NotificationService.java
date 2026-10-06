package com.example.springbasics.notification;

import com.example.springbasics.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationSender defaultSender;
    private final NotificationSender smsSender;

    @Autowired
    public NotificationService(NotificationSender defaultSender,
                               @Qualifier("smsSender") NotificationSender smsSender) {
        this.defaultSender = defaultSender;
        this.smsSender = smsSender;
        System.out.println("[NotificationService] создан: default=" + defaultSender.getClass().getSimpleName()
                + ", sms=" + smsSender.getClass().getSimpleName());
    }

    public void notifyOrderCreated(Order order) {
        defaultSender.send("Заказ #" + order.id() + " создан: " + order.product()
                + " x" + order.quantity() + ", сумма " + order.total());
    }

    public void notifyUrgent(String message) {
        smsSender.send(message);
    }
}
