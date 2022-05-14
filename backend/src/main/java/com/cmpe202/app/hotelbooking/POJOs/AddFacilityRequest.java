package com.cmpe202.app.hotelbooking.POJOs;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddFacilityRequest {
	
	@NotEmpty
	String facilityName;

	double price;
}
