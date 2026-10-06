package com.example.springbasics.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("emailSender")
@Primary
public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}
