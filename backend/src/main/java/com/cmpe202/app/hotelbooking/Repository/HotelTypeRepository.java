package com.cmpe202.app.hotelbooking.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.Model.HotelType;

@Repository
public interface HotelTypeRepository extends CrudRepository<HotelType, Integer> {
    HotelType findByHotelType(String hoteltype_name);
}
