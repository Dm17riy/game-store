package com.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.printf("[EMAIL] %s: %s%n", to, message);
    }
}
