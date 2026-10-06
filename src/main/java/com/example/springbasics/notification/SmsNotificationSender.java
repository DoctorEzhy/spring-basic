package com.example.springbasics.notification;

import org.springframework.stereotype.Component;

@Component("smsSender")
public class SmsNotificationSender implements NotificationSender {

    @Override
    public void send(String message) {
        System.out.println("[SMS]   " + message);
    }
}
