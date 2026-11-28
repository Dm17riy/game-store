package com.example;

import com.example.config.ComponentScanConfig;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppComponentScan {
    public static void main(String[] args) {
        try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class)) {
            Demo.run("Component Scan", context);
        }
    }
}
