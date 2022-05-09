package com.cmpe202.app.hotelbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe202.app.hotelbooking.POJOs.MessageResponse;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.HotelRoomType;
import com.cmpe202.app.hotelbooking.model.HotelRoomTypeId;
import com.cmpe202.app.hotelbooking.model.RoomType;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;
import com.cmpe202.app.hotelbooking.repository.HotelRoomTypeRepository;
import com.cmpe202.app.hotelbooking.repository.RoomTypeRepository;

@Service
public class HotelRoomTypeService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomTypeRepository roomTypeRepository;
	
	@Autowired
	HotelRoomTypeRepository hotelRoomTypeRepository;
	
	public ResponseEntity<?> addHotelRooms(String hotelId,String roomType,
			Double basePrice,double weekendSurge,double holidaySurge,
			int roomCount ){
		System.out.println("In room service");
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}
		

		if(!roomTypeRepository.existsByRoomType(roomType)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Room Type for the given hotel"));
		}
		
		if(basePrice==null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Room Type for the given hotel"));

		}
		
		HotelRoomType hotelRoomType=new HotelRoomType();
		Hotel hotel=hotelRepository.getById(hotelId);
		
		RoomType rt=roomTypeRepository.findByRoomType(roomType);
		Boolean hrt=
		hotelRoomTypeRepository.existsByHotelAndRoomType(hotel,rt);
		if(hrt) {
			return updateHotelRooms(  hotelId, roomType,
					 basePrice, weekendSurge, holidaySurge,
					 roomCount );
		}
		
		hotelRoomType.setHotel(hotel);
		hotelRoomType.setRoomType(rt);
		hotelRoomType.setRoomCount(roomCount);
		hotelRoomType.setBasePrice(basePrice);
		hotelRoomType.setHolidaySurge(holidaySurge);
		hotelRoomType.setWeekendSurge(weekendSurge);

		HotelRoomTypeId hotelRoomTypeId=new HotelRoomTypeId("","");
		hotelRoomType.setId(hotelRoomTypeId);
		hotelRoomTypeRepository.save(hotelRoomType);
		
		return ResponseEntity.ok(new MessageResponse("Rooms added to hotel!"));
		
	}
	
	
	
	public ResponseEntity<?> updateHotelRooms(String hotelId,String roomType,
			Double basePrice,double weekendSurge,double holidaySurge,
			int roomCount ){
		
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}
		

		if(!roomTypeRepository.existsByRoomType(roomType)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Room Type for the given hotel"));
		}
		
		if(basePrice==null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Room Type for the given hotel"));

		}
		
		//HotelRoomType hotelRoomType=new HotelRoomType();
		Hotel hotel=hotelRepository.getById(hotelId);
		RoomType rt=roomTypeRepository.findByRoomType(roomType);
		
		HotelRoomType hotelRoomType=
		hotelRoomTypeRepository.getById(new HotelRoomTypeId(hotel.getId(),rt.getId()));
		
		//hotelRoomType.setHotel(hotel);
		
		//hotelRoomType.setRoomType(rt);
		hotelRoomType.setRoomCount(roomCount);
		hotelRoomType.setBasePrice(basePrice);
		hotelRoomType.setHolidaySurge(holidaySurge);
		hotelRoomType.setWeekendSurge(weekendSurge);

		//HotelRoomTypeId hotelRoomTypeId=new HotelRoomTypeId("","");
		//hotelRoomType.setId(hotelRoomTypeId);
		hotelRoomTypeRepository.save(hotelRoomType);
		
		return ResponseEntity.ok(new MessageResponse("Rooms added to hotel!"));
		
	}
	
	public ResponseEntity<?> getHotelRooms(String hotelId){
		
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}
		System.out.println(hotelId);
		Hotel hotel=hotelRepository.getById(hotelId);
		
		//System.out.println(hotel);
		List<HotelRoomType> rt=
				hotelRoomTypeRepository.findHotelRoomTypeById_HotelId(hotelId);
		
		return ResponseEntity.ok(rt);
		
	}
	

}
