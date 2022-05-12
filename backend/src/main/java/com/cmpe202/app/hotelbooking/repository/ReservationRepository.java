package com.cmpe202.app.hotelbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cmpe202.app.hotelbooking.model.Reservation;



public interface ReservationRepository extends JpaRepository<Reservation, String>{

	

	List<Reservation> findByUser_Id(int id);
	
	List<Reservation> findByRoom_Id_HotelId(String hotelId);

}
