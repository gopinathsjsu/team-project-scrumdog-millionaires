package com.cmpe202.app.hotelbooking.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cmpe202.app.hotelbooking.Model.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {
    Food findByFoodName(String foodName);

}