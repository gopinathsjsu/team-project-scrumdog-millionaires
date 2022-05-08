package com.cmpe202.app.hotelbooking.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
@Table(name = "City",catalog = "hotel_app")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","$$_hibernate_interceptor"})
public class City {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int id;
	
	@Column(name = "city_name")
	@NotEmpty(message = "*Please enter your city")
	private String cityName;
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="state_id",referencedColumnName = "state_id")
	private State state;
	

}