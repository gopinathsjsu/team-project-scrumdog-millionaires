package com.cmpe202.app.hotelbooking.pricing;


import java.time.LocalDate;


import com.cmpe202.app.hotelbooking.model.Room;

public class VacationRoomPrice extends RoomPrice {
	

	boolean isWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
		   return !(date.isBefore(startDate) || date.isAfter(endDate));
		}

	@Override
	public Double calculateRoomPrice(LocalDate date, Room room) {
		Double roomPrice = 0.0;
	
		
		if(room.getVactionSurge()>0.0 && (isWithinRange(date,LocalDate.parse("2022-12-25"),LocalDate.parse("2023-01-01"))||
				isWithinRange(date,LocalDate.parse("2022-11-20"),LocalDate.parse("2022-11-26"))||
						isWithinRange(date,LocalDate.parse("2022-06-01"),LocalDate.parse("2022-08-31")))){
			roomPrice=room.getBasePrice()+(room.getBasePrice()*(room.getVactionSurge()/100));
			return roomPrice;
					}else
						return nextRoomPrice.calculateRoomPrice(date, room);
	}

	
}
