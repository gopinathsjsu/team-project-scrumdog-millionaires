package com.cmpe202.app.hotelbooking.Repository;

import org.springframework.data.repository.CrudRepository;


import com.cmpe202.app.hotelbooking.Model.State;

public interface StateRepository extends CrudRepository<State, Integer>{
    State findByStateName(String stateName);
}