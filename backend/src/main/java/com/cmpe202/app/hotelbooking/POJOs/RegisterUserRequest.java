package com.cmpe202.app.hotelbooking.POJOs;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterUserRequest {
	
	String firstName;
	
	String lastName;
	
	@NotBlank
	String email;
	
	@NotBlank
	String password;
	
	String number;
	
	String street;
	
	String city;
	
	String state;
	
	String zip;
	
	@NotBlank
	String role;
	
	String hotelId;

}
