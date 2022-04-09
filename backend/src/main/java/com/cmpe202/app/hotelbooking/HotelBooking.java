package com.cmpe202.app.hotelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cmpe202.app.hotelbooking.Repository")
@SpringBootApplication
public class HotelBooking {
    public static void main(String[] args) {
        SpringApplication.run(HotelBooking.class, args);
    }
}