package com.cmpe202.app.hotelbooking.POJOs;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ReservationRequest {
	
	
	@NotEmpty
	String hotelId;
	
	@NotEmpty
	String roomType;
	
	@NotEmpty
	String startDate;
	
	@NotEmpty
	String endDate;
	
	int userId;
	
	int roomCount;
	int peopleCount;
	boolean gym;
	boolean breakfast;
	boolean pool;
	boolean parking;
	boolean allMeals;

}
