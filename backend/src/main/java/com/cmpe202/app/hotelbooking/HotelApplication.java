package com.cmpe202.app.hotelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cmpe202.app.hotelbooking.repository")
public class HotelApplication {
	  public static void main(String[] args) {
	        SpringApplication.run(HotelApplication.class, args);
	    }
}
