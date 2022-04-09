package com.cmpe202.app.hotelbooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.Model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
     static Hotel findByHotelName(String hotel_name) {
          return null;
     }

}