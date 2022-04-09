package com.cmpe202.app.hotelbooking.Repository;


import org.springframework.data.repository.CrudRepository;

import com.cmpe202.app.hotelbooking.Model.City;

public interface CityRepository extends CrudRepository<City, Integer>{
    City findByCityName(String cityName);
}

