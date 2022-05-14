package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpe202.app.hotelbooking.model.HotelFacility;
import com.cmpe202.app.hotelbooking.model.HotelFacilityId;

public interface HotelFacilityRepository extends JpaRepository<HotelFacility, HotelFacilityId>{
	

}
