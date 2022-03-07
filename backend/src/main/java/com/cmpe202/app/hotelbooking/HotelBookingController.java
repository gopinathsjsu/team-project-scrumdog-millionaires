package com.cmpe202.app.hotelbooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelBookingController {
	
	@GetMapping("/")
	public String index() {
		return "Welcome to ScRuM Hotels!";
	}

}
