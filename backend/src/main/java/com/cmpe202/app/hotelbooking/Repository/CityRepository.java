package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.cmpe202.app.hotelbooking.model.City;

public interface CityRepository extends CrudRepository<City, Integer>{
	City findByCityName(String cityName);
}
