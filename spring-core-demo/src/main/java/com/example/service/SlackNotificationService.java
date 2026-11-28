package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class SlackNotificationService implements NotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.printf("[SLACK] %s: %s%n", to, message);
    }
}
