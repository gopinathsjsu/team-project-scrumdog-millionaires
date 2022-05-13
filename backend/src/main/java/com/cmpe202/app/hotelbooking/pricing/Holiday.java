package com.cmpe202.app.hotelbooking.pricing;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Holiday {
	
	private  Set<LocalDate> holidays=new HashSet<>();
	
	private static Holiday holidayInstance;

	
	private Holiday() {
		holidays.add(LocalDate.of(2022, 01, 01));
		holidays.add(LocalDate.of(2022, 01, 01));
		holidays.add(LocalDate.of(2022, 01, 17));
		holidays.add(LocalDate.of(2022, 02, 21));
		holidays.add(LocalDate.of(2022, 05, 30));
		holidays.add(LocalDate.of(2022, 06, 19));
		holidays.add(LocalDate.of(2022, 07, 04));
		holidays.add(LocalDate.of(2022, 9, 05));
		holidays.add(LocalDate.of(2022, 10, 10));
		holidays.add(LocalDate.of(2022, 11, 11));
		holidays.add(LocalDate.of(2022, 11, 24));
		holidays.add(LocalDate.of(2022, 12, 25));
	}
	
	public static Holiday getInstance() {
		if(holidayInstance==null) {
			holidayInstance=new Holiday();
		}
		return holidayInstance;
	}
	
	public Set<LocalDate> getCategoryCap() {
		return this.holidays;
	}

	public Set<LocalDate> getHolidays() {
		return holidays;
	}

}
