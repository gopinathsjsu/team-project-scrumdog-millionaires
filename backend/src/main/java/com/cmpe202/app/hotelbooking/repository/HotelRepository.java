package com.cmpe202.app.hotelbooking.repository;

import com.cmpe202.app.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
	boolean existsById(String id);
}