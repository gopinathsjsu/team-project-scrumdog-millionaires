package com.cmpe202.app.hotelbooking.pricing;

import java.time.LocalDate;

import com.cmpe202.app.hotelbooking.model.Room;

public abstract class RoomPrice {

	protected RoomPrice nextRoomPrice;

	public abstract Double calculateRoomPrice(LocalDate date, Room room);

	public void nextHandler(RoomPrice next) {
		this.nextRoomPrice = next;
	}
}
