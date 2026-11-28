package com.example;

import com.example.config.AppConfig;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppJavaConfig {
    public static void main(String[] args) {
        try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Demo.run("Java Config", context);
        }
    }
}
