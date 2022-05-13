package com.cmpe202.app.hotelbooking.pricing;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.cmpe202.app.hotelbooking.model.Room;

public class HolidayRoomPrice extends RoomPrice {

	@Override
	public Double calculateRoomPrice(LocalDate date, Room room) {
		Double roomPrice = 0.0;
		if (room.getHolidaySurge() > 0.0) {

			Holiday holidays = Holiday.getInstance();
			Set<LocalDate> usaHolidayList = holidays.getHolidays();
			for (LocalDate dateObj : usaHolidayList) {
				if (dateObj.isEqual(date)) {
					roomPrice = room.getBasePrice() + (room.getBasePrice() * (room.getHolidaySurge() / 100));
					break;
				}
			}

		}
		if (roomPrice > 0.0)
			return roomPrice;
		else
			return nextRoomPrice.calculateRoomPrice(date, room);
	}

}
