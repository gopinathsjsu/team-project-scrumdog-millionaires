package com.cmpe202.app.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class CustomerUser extends User{
	
	@Column(name = "customer_points")
	private int customerPoints;
}
