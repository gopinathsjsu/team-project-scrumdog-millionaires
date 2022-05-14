package com.cmpe202.app.hotelbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe202.app.hotelbooking.POJOs.MessageResponse;
import com.cmpe202.app.hotelbooking.model.EFacility;
import com.cmpe202.app.hotelbooking.model.Facility;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.HotelFacility;
import com.cmpe202.app.hotelbooking.model.HotelFacilityId;
import com.cmpe202.app.hotelbooking.repository.FacilityRepository;
import com.cmpe202.app.hotelbooking.repository.HotelFacilityRepository;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;

@Service
public class FacilityService {

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	FacilityRepository facilityRepository;

	@Autowired
	HotelFacilityRepository hotelFacilityRepository;

	public ResponseEntity<?> addHotelFacility(String hotelId, String facilityName, double price) {

		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid hotel ID"));

		}

		EFacility efacility;
		try {
			efacility = EFacility.valueOf(facilityName);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid facility Name"));

		}
		System.out.println("efacility: "+efacility.toString());
		Facility facility = facilityRepository.findByFacilityName(efacility);
		System.out.println("facility: "+facility);
		
		
		  HotelFacilityId hotelFacilityId = new HotelFacilityId(facility.getId(),
		  hotelId);
		  
		  HotelFacility hotelFacility = new HotelFacility();
		  hotelFacility.setId(hotelFacilityId);
		  hotelFacility.setHotel(hotelRepository.getById(hotelId));
		  hotelFacility.setFacility(facility); hotelFacility.setPrice(price);
		  
		  HotelFacility shotelFacility = hotelFacilityRepository.save(hotelFacility);
		 
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shotelFacility);

	}

	public ResponseEntity<?> updateHotelFacility(String hotelId, String facilityName, double price) {
		
		
		System.out.println("Facility: "+facilityName);
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid hotel ID"));

		}

		EFacility efacility;
		try {
			efacility = EFacility.valueOf(facilityName);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid facility Name"));

		}

		Facility facility = facilityRepository.findByFacilityName(efacility);

		HotelFacilityId hotelFacilityId = new HotelFacilityId(facility.getId(), hotelId);

		HotelFacility hotelFacility = hotelFacilityRepository.getById(hotelFacilityId);

		if (hotelFacility == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: hotel does not have this facility"));

		}

		hotelFacility.setPrice(price);

		HotelFacility shotelFacility = hotelFacilityRepository.save(hotelFacility);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(shotelFacility);

	}
	
	
	public ResponseEntity<?> deleteHotelFacility(String hotelId, String facilityName) {
		
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid hotel ID"));

		}

		EFacility efacility;
		try {
			efacility = EFacility.valueOf(facilityName);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid facility Name"));

		}

		Facility facility = facilityRepository.findByFacilityName(efacility);

		HotelFacilityId hotelFacilityId = new HotelFacilityId(facility.getId(), hotelId);

		HotelFacility hotelFacility = hotelFacilityRepository.getById(hotelFacilityId);

		if (hotelFacility == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: hotel does not have this facility"));

		}
		hotelFacilityRepository.delete(hotelFacility);
		
		return ResponseEntity.ok(new MessageResponse("facility deleted successfully!"));
		
		
	}
	
	
	public ResponseEntity<?> getAllHotelFacilities(String hotelId) {
		if (!hotelRepository.existsById(hotelId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: invalid hotel ID"));

		}
		
		Hotel hotel=hotelRepository.findById(hotelId).get();
		
		List<HotelFacility> facilities=hotel.getFacilities();
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(facilities);

		
	}
		

}
