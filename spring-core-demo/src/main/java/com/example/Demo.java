package com.example;

import com.example.model.User;
import com.example.service.NotificationService;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.context.ApplicationContext;

final class Demo {
    private Demo() {
    }

    static void run(String configuration, ApplicationContext context) {
        System.out.println("=== " + configuration + " ===");
        UserService users = context.getBean(UserService.class);
        ProductService products = context.getBean(ProductService.class);

        User user = users.registerUser("larionov", "larionov@example.com");
        users.registerUser("student", "student@example.com");
        System.out.println("Користувачі: " + users.getAllUsers());
        System.out.println("Пошук за ID: " + users.getUserById(user.getId()));
        try {
            users.registerUser(user.getUsername(), "duplicate@example.com");
        } catch (IllegalArgumentException exception) {
            System.out.println("Повторна реєстрація: " + exception.getMessage());
        }

        products.addProduct("Keyboard", 1499.0);
        products.addProduct("Mouse", 899.0);
        System.out.println("Товари: " + products.getAllProducts());
        System.out.println("Біни NotificationService: " + context.getBeansOfType(NotificationService.class).keySet());
        System.out.println("Основний NotificationService: " + context.getBean(NotificationService.class).getClass().getSimpleName());
    }
}
