package com.example.config;

import com.example.repository.InMemoryProductRepository;
import com.example.repository.InMemoryUserRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.EmailNotificationService;
import com.example.service.NotificationService;
import com.example.service.ProductService;
import com.example.service.SlackNotificationService;
import com.example.service.SmsNotificationService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    @Primary
    public NotificationService emailNotificationService() {
        return new EmailNotificationService();
    }

    @Bean
    public NotificationService smsNotificationService() {
        return new SmsNotificationService();
    }

    @Bean
    public NotificationService slackNotificationService() {
        return new SlackNotificationService();
    }

    @Bean
    public UserService userService(UserRepository repository,
                                   @Qualifier("emailNotificationService") NotificationService notifications) {
        UserService service = new UserService(repository);
        service.setNotificationService(notifications);
        return service;
    }

    @Bean
    public ProductService productService(ProductRepository repository,
                                         @Qualifier("slackNotificationService") NotificationService notifications) {
        return new ProductService(repository, notifications);
    }
}
