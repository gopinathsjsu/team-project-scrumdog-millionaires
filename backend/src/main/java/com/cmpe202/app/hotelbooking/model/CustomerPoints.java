package com.cmpe202.app.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@Entity @JsonAutoDetect
@Table(name = "CUSTOMER_POINTS",catalog = "hotel_app")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class CustomerPoints {
	

	@Id
	@Column(name = "POINTS_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String pointsId;
	
	@Column(name = "POINTS")
	private int points;
	
	/*
	 * @JsonIgnoreProperties
	 * 
	 * @OneToOne(mappedBy = "points", cascade = CascadeType.ALL) private User user;
	 */

}