package com.cmpe202.app.hotelbooking.controller;

import java.time.LocalDate;

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

import com.cmpe202.app.hotelbooking.POJOs.FinalPriceQuoteRequest;
import com.cmpe202.app.hotelbooking.POJOs.HotelRoomTypeReq;
import com.cmpe202.app.hotelbooking.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	 @PostMapping
	public ResponseEntity<?> addHotelRooms(@RequestBody @Valid
			HotelRoomTypeReq roomTypeReq
			){
	return 	roomService.addHotelRooms( roomTypeReq.getHotelId(), roomTypeReq.getRoomType(),
			roomTypeReq.getBasePrice(), roomTypeReq.getVacationSurge(),roomTypeReq.getWeekendSurge(), roomTypeReq.getHolidaySurge(),
			roomTypeReq.getRoomCount() );
	}
	 
	 @PutMapping
	 public ResponseEntity<?> updateHotelRooms(@RequestBody @Valid
				HotelRoomTypeReq roomTypeReq
				){
		return 	roomService.updateHotelRooms( roomTypeReq.getHotelId(), roomTypeReq.getRoomType(),
				roomTypeReq.getBasePrice(),roomTypeReq.getVacationSurge(), roomTypeReq.getWeekendSurge(), roomTypeReq.getHolidaySurge(),
				roomTypeReq.getRoomCount() );
		}
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getHotelRooms(@PathVariable String id
				){
		 
		 System.out.println("In get mapping");
		 return roomService.getHotelRooms( id);
	 }
	 
	 @GetMapping("/search")
	    public ResponseEntity<?> searchHotelRooms(@RequestParam String city,@RequestParam String startDate,
	    		@RequestParam String
				endDate,@RequestParam int roomCount,@RequestParam int adultsPerRoom){
	       return roomService.searchHotelRooms(city,LocalDate.parse(startDate), LocalDate.parse(endDate), roomCount, adultsPerRoom);
	    }
	 
	 @GetMapping("/price")
	 public ResponseEntity<?> getRoomPrice(@RequestParam("hotelid") String hotelId,@RequestParam("roomtype") String roomType,
			 @RequestParam("startdate") String startDate,@RequestParam("enddate") String endDate){
		 
		 return roomService.getRoomPrice(hotelId, roomType, startDate, endDate);
	 }
	 
	 @PostMapping("/totalprice")
	 public ResponseEntity<?> getRoomTotalPrice(@Valid @RequestBody FinalPriceQuoteRequest finalpriceReq
			 ){
		 
		 return roomService.getTotalRoomPrice(finalpriceReq);
	 }
	 

}
