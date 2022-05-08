package com.cmpe202.app.hotelbooking.Service;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmpe202.app.hotelbooking.Model.HotelType;
import com.cmpe202.app.hotelbooking.Model.Hotel;
import com.cmpe202.app.hotelbooking.Repository.HotelRepository;
import com.cmpe202.app.hotelbooking.Repository.HotelTypeRepository;

@Service
public class HotelService {
    private HotelTypeRepository hotelTypeRepository;
    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository,HotelTypeRepository hotelTypeRepository){
        this.hotelRepository=hotelRepository;
        this.hotelTypeRepository=hotelTypeRepository;
    }
    public Hotel findByHotelName(String hotel_name){
        return HotelRepository.findByHotelName(hotel_name);
    }
}
