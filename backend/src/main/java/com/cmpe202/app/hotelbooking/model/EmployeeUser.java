package com.cmpe202.app.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class EmployeeUser extends User{
	
	@Column(name = "Emp_is_admin")
	private boolean empIsAdmin;
	
	@Column(name = "hotel_id")
	private int hotelId;

}
