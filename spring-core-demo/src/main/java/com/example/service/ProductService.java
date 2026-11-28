package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    public ProductService(ProductRepository productRepository,
                          @Qualifier("slackNotificationService") NotificationService notificationService) {
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }

    public void addProduct(String name, double price) {
        productRepository.save(new Product(name, price));
        notificationService.sendNotification("#products", "Додано товар " + name + " (" + price + " грн)");
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
