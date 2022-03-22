package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;


import com.cmpe202.app.hotelbooking.model.State;

public interface StateRepository extends CrudRepository<State, Integer>{
	State findByStateName(String stateName);
}
