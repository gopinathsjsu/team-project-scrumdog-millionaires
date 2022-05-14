package com.cmpe202.app.hotelbooking.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe202.app.hotelbooking.POJOs.ReservationRequest;
import com.cmpe202.app.hotelbooking.POJOs.ReservationUpdateReq;
import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.service.ReservationService;
import com.cmpe202.app.hotelbooking.service.RoomService;
import com.cmpe202.app.hotelbooking.service.UserService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RoomService roomService;
	
	 @GetMapping("user/{userid}")
	 ResponseEntity<?> getUserReservations(@PathVariable String userid){
		 
			User userExists = userService.findUserByID(Integer.parseInt(userid))
					.orElseThrow(() -> new EntityNotFoundException("Invalid user ID"));
		 return reservationService.getUserReservations(Integer.parseInt(userid));
	 }
	 
	 @PutMapping("{reservationid}")
	 ResponseEntity<?> updateReservation(@PathVariable String reservationid,
			 @RequestBody @Valid
				ReservationUpdateReq resev){
		 return reservationService.updateReservation(reservationid,resev.getStatus());
	 }
	 
	 @GetMapping("hotel/{hotelId}")
	 ResponseEntity<?> getHotelReservations(@PathVariable String hotelId){
		 return reservationService.getHotelReservations(hotelId);

	 }
	 
	 @PostMapping
	 ResponseEntity<?> makeReservations(@Valid @RequestBody ReservationRequest resevReq){
		
		System.out.println("In make reservation");
		 return roomService.makeReservations(resevReq);

	 }
	 
	
}
