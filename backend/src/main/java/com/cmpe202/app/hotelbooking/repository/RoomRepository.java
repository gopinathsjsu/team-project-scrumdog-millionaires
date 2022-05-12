package com.cmpe202.app.hotelbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.Room;
import com.cmpe202.app.hotelbooking.model.HotelRoomTypeId;
import com.cmpe202.app.hotelbooking.model.RoomType;

@Repository
public interface RoomRepository  extends JpaRepository<Room, HotelRoomTypeId>{

	List<Room> findRoomById_HotelId(String hotelId);
	
	Room findRoomById_HotelIdAndId_TypeId(String hotelId,String roomTyepeId);
	
	List<Room> findByHotel(Hotel hotel);
	boolean existsByHotelAndRoomType(Hotel hotel,RoomType roomtype);
	
	List<Room> findByHotelAndRoomType(Hotel hotel,RoomType roomtype);
	
	@Query(value="select r.* from room r,hotel h where r.hotel_id=h.id "
			+ "and h.city=?1 and r.room_type_type_id=?2",nativeQuery = true)
	List<Room> findByRoomByTypeAndCity(String city,String typeId);
}
