package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.printf("[SMS] %s: %s%n", to, message);
    }
}
