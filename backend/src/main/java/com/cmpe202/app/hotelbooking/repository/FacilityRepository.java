package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpe202.app.hotelbooking.model.EFacility;
import com.cmpe202.app.hotelbooking.model.Facility;

public interface FacilityRepository extends JpaRepository<Facility, String>{
	boolean existsByFacilityName(EFacility facility);
	
	
	Facility findByFacilityName(EFacility facility);
}
