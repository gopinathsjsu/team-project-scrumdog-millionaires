package com.cmpe202.app.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpe202.app.hotelbooking.model.CustomerPoints;

public interface CustomerPointsRepository extends JpaRepository<CustomerPoints, String>{

}
