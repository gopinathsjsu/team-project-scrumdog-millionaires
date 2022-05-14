package com.cmpe202.app.hotelbooking.POJOs;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FinalPriceQuoteRequest {
	
	@NotEmpty
	String hotelId;
	
	@NotEmpty
	String roomType;
	
	
	int userId;

	String startDate;
	String endDate;
	int roomCount;
	int peopleCount;
	boolean gym;
	boolean breakfast;
	boolean pool;
	boolean parking;
	boolean allMeals;

}
