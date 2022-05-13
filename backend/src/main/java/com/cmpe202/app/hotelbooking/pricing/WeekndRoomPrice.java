package com.cmpe202.app.hotelbooking.pricing;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Set;

import com.cmpe202.app.hotelbooking.model.Room;
import java.time.DayOfWeek;

public class WeekndRoomPrice extends RoomPrice {


	@Override
	public Double calculateRoomPrice(LocalDate date, Room room) {
		Double roomPrice = 0.0;
		if(room.getWeekendSurge()==0.0) {
			return 0.0;
		}
		
		DayOfWeek day=DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		if(room.getWeekendSurge()>0.0 &&(day==DayOfWeek.SUNDAY||day== DayOfWeek.SATURDAY)) {
			roomPrice=room.getBasePrice()+(room.getBasePrice()*(room.getWeekendSurge()/100));
			return roomPrice;
		}else
			return nextRoomPrice.calculateRoomPrice(date, room);
	}


}
