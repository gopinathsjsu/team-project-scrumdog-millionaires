package com.cmpe202.app.hotelbooking.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTEL_EMPLOYEE",catalog = "HOTEL_APP")
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class HotelEmployee{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOTEL_EMP_ID")
	private int hotelEmplyeeId;
	

	
	@OneToOne(mappedBy="hotelEmployee")
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	 @JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	/*
	 * @JsonIgnoreProperties
	 * 
	 * @OneToOne(mappedBy = "hotelEmployee", cascade = CascadeType.ALL) private User
	 * user;
	 */
}
