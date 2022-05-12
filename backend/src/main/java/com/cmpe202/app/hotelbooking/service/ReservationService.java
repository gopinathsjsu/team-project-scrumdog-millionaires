package com.cmpe202.app.hotelbooking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.cmpe202.app.hotelbooking.POJOs.MessageResponse;
import com.cmpe202.app.hotelbooking.model.EStatus;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.Reservation;
import com.cmpe202.app.hotelbooking.model.Room;
import com.cmpe202.app.hotelbooking.model.RoomType;
import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;
import com.cmpe202.app.hotelbooking.repository.ReservationRepository;
import com.cmpe202.app.hotelbooking.repository.RoomRepository;
import com.cmpe202.app.hotelbooking.repository.RoomTypeRepository;
import com.cmpe202.app.hotelbooking.repository.UserRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	private UserRepository userRepository;

	public ResponseEntity<?> getUserReservations(int userId) {

		try {

			List<Reservation> reservations = reservationRepository.findByUser_Id(userId);

			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(reservations);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(e.toString()));

		}
	}

	public ResponseEntity<?> updateReservation(String reservationId, String status) {
		Reservation resv = null;
		try {
			resv = reservationRepository.findById(reservationId)
					.orElseThrow(() -> new EntityNotFoundException("Invalid reservation ID"));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: reservation not found"));

		}

		EStatus resvStatus;
		try {
			resvStatus = EStatus.valueOf(status);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: inavlid status"));

		}
		resv.setStatus(resvStatus);

		Reservation newresv = reservationRepository.save(resv);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newresv);

	}

	public ResponseEntity<?> getHotelReservations(String hotelId) {

		Hotel hotel;
		try {
			hotel = hotelRepository.findById(hotelId)
					.orElseThrow(() -> new EntityNotFoundException("Invalid hotel ID"));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: hotel not found"));

		}
		
		List<Reservation> resv = reservationRepository.findByRoom_Id_HotelId(hotelId);
	
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resv);

	}

}
