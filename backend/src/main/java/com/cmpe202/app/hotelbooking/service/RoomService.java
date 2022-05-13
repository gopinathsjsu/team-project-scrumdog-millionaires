package com.cmpe202.app.hotelbooking.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.temporal.ChronoUnit;

import com.cmpe202.app.hotelbooking.POJOs.MessageResponse;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.Room;
import com.cmpe202.app.hotelbooking.model.HotelRoomTypeId;
import com.cmpe202.app.hotelbooking.model.RoomType;
import com.cmpe202.app.hotelbooking.pricing.BaseRoomPrice;
import com.cmpe202.app.hotelbooking.pricing.HolidayRoomPrice;
import com.cmpe202.app.hotelbooking.pricing.VacationRoomPrice;
import com.cmpe202.app.hotelbooking.pricing.WeekndRoomPrice;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;
import com.cmpe202.app.hotelbooking.repository.RoomRepository;
import com.cmpe202.app.hotelbooking.repository.RoomTypeRepository;

@Service
public class RoomService {

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	RoomRepository roomRepository;

	public ResponseEntity<?> addHotelRooms(String hotelId, String roomType, Double basePrice,double vacationSurge, double weekendSurge,
			double holidaySurge, int roomCount) {
		System.out.println("In room service");
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}

		if (!roomTypeRepository.existsByRoomType(roomType)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid Room Type for the given hotel"));
		}

		if (basePrice == null) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid Room Type for the given hotel"));

		}

		if (weekendSurge >= 100.0 || holidaySurge >= 100.0||vacationSurge>=100) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Surges cannot be greater than 100"));

		}

		Room hotelRoomType = new Room();
		Hotel hotel = hotelRepository.getById(hotelId);

		RoomType rt = roomTypeRepository.findByRoomType(roomType);
		Boolean hrt = roomRepository.existsByHotelAndRoomType(hotel, rt);
		if (hrt) {
			return updateHotelRooms(hotelId, roomType, basePrice,vacationSurge, weekendSurge, holidaySurge, roomCount);
		}

		hotelRoomType.setHotel(hotel);
		hotelRoomType.setRoomType(rt);
		hotelRoomType.setRoomCount(roomCount);
		hotelRoomType.setBasePrice(basePrice);
		hotelRoomType.setHolidaySurge(holidaySurge);
		hotelRoomType.setWeekendSurge(weekendSurge);
		hotelRoomType.setVactionSurge(vacationSurge);

		HotelRoomTypeId hotelRoomTypeId = new HotelRoomTypeId("", "");
		hotelRoomType.setId(hotelRoomTypeId);
		roomRepository.save(hotelRoomType);

		return ResponseEntity.ok(new MessageResponse("Rooms added to hotel!"));

	}

	public ResponseEntity<?> updateHotelRooms(String hotelId, String roomType, Double basePrice, 
			double vacationSurge,double weekendSurge,
			double holidaySurge, int roomCount) {

		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}

		if (!roomTypeRepository.existsByRoomType(roomType)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid Room Type for the given hotel"));
		}

		if (basePrice == null) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid Room Type for the given hotel"));

		}
		
		if (weekendSurge >= 100.0 || holidaySurge >= 100.0||vacationSurge>=100) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Surges cannot be greater than 100"));

		}

		// HotelRoomType hotelRoomType=new HotelRoomType();
		Hotel hotel = hotelRepository.getById(hotelId);
		RoomType rt = roomTypeRepository.findByRoomType(roomType);

		Room hotelRoomType = roomRepository.getById(new HotelRoomTypeId(hotel.getId(), rt.getId()));

		if (hotelRoomType == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid selection"));

		}


		hotelRoomType.setRoomCount(roomCount);
		hotelRoomType.setBasePrice(basePrice);
		hotelRoomType.setHolidaySurge(holidaySurge);
		hotelRoomType.setWeekendSurge(weekendSurge);
		hotelRoomType.setVactionSurge(vacationSurge);

		// HotelRoomTypeId hotelRoomTypeId=new HotelRoomTypeId("","");
		// hotelRoomType.setId(hotelRoomTypeId);
		roomRepository.save(hotelRoomType);

		return ResponseEntity.ok(new MessageResponse("Rooms added to hotel!"));

	}

	public ResponseEntity<?> getHotelRooms(String hotelId) {

		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}
		System.out.println(hotelId);
		Hotel hotel = hotelRepository.getById(hotelId);

		// System.out.println(hotel);
		List<Room> rt = roomRepository.findRoomById_HotelId(hotelId);

		return ResponseEntity.ok(rt);

	}

	public ResponseEntity<?> searchHotelRooms(String city, LocalDate startDate, LocalDate endDate, int roomCount,
			int adultsPerRoom) {

		if (roomCount == 0 || adultsPerRoom == 0 || adultsPerRoom > 6) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid room/adults count"));
		}

		List<Room> selectedrooms = new ArrayList<>();
		if (adultsPerRoom == 1) {
			List<Room> room = roomRepository.findByRoomByTypeAndCity(city,

					roomTypeRepository.findByRoomType("SINGLE").getId());

			selectedrooms.addAll(room);
		}
		if (adultsPerRoom <= 2) {
			List<Room> room = roomRepository.findByRoomByTypeAndCity(city,
					roomTypeRepository.findByRoomType("DOUBLE").getId());

			selectedrooms.addAll(room);
		}
		if (adultsPerRoom <= 3) {
			List<Room> room = roomRepository.findByRoomByTypeAndCity(city,
					roomTypeRepository.findByRoomType("TRIPLE").getId());

			selectedrooms.addAll(room);
		}
		if (adultsPerRoom <= 4) {
			List<Room> room = roomRepository.findByRoomByTypeAndCity(city,
					roomTypeRepository.findByRoomType("QUAD").getId());

			selectedrooms.addAll(room);
		}
		if (adultsPerRoom <= 6) {
			List<Room> room = roomRepository.findByRoomByTypeAndCity(city,
					roomTypeRepository.findByRoomType("SUITE").getId());

			selectedrooms.addAll(room);
		}

		return ResponseEntity.ok(selectedrooms);
	}
	
	public double calculateTotalRoomPrice(Room room,LocalDate stDate, LocalDate eDate) {
		BaseRoomPrice base = new BaseRoomPrice();

		VacationRoomPrice vacationPrice = new VacationRoomPrice();
		WeekndRoomPrice weekendPrice = new WeekndRoomPrice();
		HolidayRoomPrice holidayPrice = new HolidayRoomPrice();

		vacationPrice.nextHandler(holidayPrice);
		holidayPrice.nextHandler(weekendPrice);
		weekendPrice.nextHandler(base);
		double totalPrice = 0.0;
		for (LocalDate date = stDate; date.isBefore(eDate); date = date.plusDays(1)) {
			totalPrice += vacationPrice.calculateRoomPrice(date, room);

		}
		return totalPrice;
	}

	public ResponseEntity<?> getRoomPrice(String hotelId, String roomType, String startDate, String endDate) {

		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Hotel ID"));
		}

		if (!roomTypeRepository.existsByRoomType(roomType)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid Room Type for the given hotel"));
		}
		
		LocalDate stDate,eDate;
		try {
		 stDate = LocalDate.parse(startDate);
		 eDate = LocalDate.parse(endDate);
		}catch(DateTimeParseException e) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Invalid dates, use YYYY-MM-DD format"));
		}
		LocalDate currentDate = LocalDate.now();

		if (stDate.isBefore(currentDate) || stDate.isAfter(eDate) || eDate.isBefore(currentDate)
				|| stDate.equals(eDate)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid date selection"));

		}
		
		long daysBewteen=stDate.until(eDate,ChronoUnit.DAYS);
		if(daysBewteen>7) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: More than 7 days selected"));

		}

		RoomType rt = roomTypeRepository.findByRoomType(roomType);

		Room room = roomRepository.getById(new HotelRoomTypeId(hotelId, rt.getId()));
		
		double totalPrice=calculateTotalRoomPrice( room, stDate, eDate);
		double pricePerNight=Math.round(totalPrice/daysBewteen);

		return ResponseEntity.ok(pricePerNight);
	}

}
