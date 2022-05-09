package com.cmpe202.app.hotelbooking.POJOs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
public class HotelRoomTypeReq {
	
	@NotEmpty
	String hotelId;
	
	@NotEmpty
	 String roomType;
	 
	@NotNull
	Double basePrice;
	
	double weekendSurge;
	
	double holidaySurge;
	
	int roomCount;


}
