package com.expiryalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExpiryAlertApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpiryAlertApplication.class, args);
    }
} 