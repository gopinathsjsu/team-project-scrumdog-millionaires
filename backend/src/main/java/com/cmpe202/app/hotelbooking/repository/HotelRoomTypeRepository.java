package com.cmpe202.app.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.HotelRoomType;
import com.cmpe202.app.hotelbooking.model.HotelRoomTypeId;
import com.cmpe202.app.hotelbooking.model.RoomType;

@Repository
public interface HotelRoomTypeRepository  extends JpaRepository<HotelRoomType, HotelRoomTypeId>{

	List<HotelRoomType> findHotelRoomTypeById_HotelId(String hotelId);
	
	List<HotelRoomType> findByHotel(Hotel hotel);
	boolean existsByHotelAndRoomType(Hotel hotel,RoomType roomtype);

}
