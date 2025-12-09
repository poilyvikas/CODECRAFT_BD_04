package com.internship.secureuserapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecureuserapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureuserapiApplication.class, args);
        System.out.println("secure api started running....");
    }
}
