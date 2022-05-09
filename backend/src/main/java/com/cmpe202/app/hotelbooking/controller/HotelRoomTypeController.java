package com.cmpe202.app.hotelbooking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe202.app.hotelbooking.POJOs.HotelRoomTypeReq;
import com.cmpe202.app.hotelbooking.repository.HotelEmployeeRepository;
import com.cmpe202.app.hotelbooking.repository.HotelRoomTypeRepository;
import com.cmpe202.app.hotelbooking.service.HotelRoomTypeService;




@RestController
@RequestMapping("/api/room")
public class HotelRoomTypeController {
	
	@Autowired
	private HotelRoomTypeService hotelRoomTypeService;
	
	 @PostMapping
	public ResponseEntity<?> addHotelRooms(@RequestBody @Valid
			HotelRoomTypeReq roomTypeReq
			){
	return 	hotelRoomTypeService.addHotelRooms( roomTypeReq.getHotelId(), roomTypeReq.getRoomType(),
			roomTypeReq.getBasePrice(), roomTypeReq.getWeekendSurge(), roomTypeReq.getHolidaySurge(),
			roomTypeReq.getRoomCount() );
	}
	 
	 @PutMapping
	 public ResponseEntity<?> updateHotelRooms(@RequestBody @Valid
				HotelRoomTypeReq roomTypeReq
				){
		return 	hotelRoomTypeService.updateHotelRooms( roomTypeReq.getHotelId(), roomTypeReq.getRoomType(),
				roomTypeReq.getBasePrice(), roomTypeReq.getWeekendSurge(), roomTypeReq.getHolidaySurge(),
				roomTypeReq.getRoomCount() );
		}
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getHotelRooms(@PathVariable String id
				){
		 
		 System.out.println("In get mapping");
		 return hotelRoomTypeService.getHotelRooms( id);
	 }
	 

}
