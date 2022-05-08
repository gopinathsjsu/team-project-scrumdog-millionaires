package com.cmpe202.app.hotelbooking.repository;

import com.cmpe202.app.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    // all crud database methods
}