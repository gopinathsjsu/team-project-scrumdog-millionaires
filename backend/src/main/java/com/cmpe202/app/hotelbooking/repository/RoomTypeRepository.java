package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.model.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, String>{
	boolean existsByRoomType(String roomtype);
	
	RoomType findByRoomType(String roomtype);
}
