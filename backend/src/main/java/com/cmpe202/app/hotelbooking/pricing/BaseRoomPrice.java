package com.cmpe202.app.hotelbooking.pricing;

import java.time.LocalDate;

import com.cmpe202.app.hotelbooking.model.Room;

public class BaseRoomPrice extends RoomPrice{

	@Override
	public Double calculateRoomPrice(LocalDate date, Room room) {
		return room.getBasePrice();
	}

}
