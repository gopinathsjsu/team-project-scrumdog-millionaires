package com.cmpe202.app.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


@Data
@Builder
@Entity
@Table(name = "hotel_facilities", catalog = "hotel_app")
@JsonAutoDetect
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class HotelFacility {
	
	@EmbeddedId
	private HotelFacilityId id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
	@JsonIgnoreProperties({"hotelRoomTypes","facilities","hibernateLazyInitializer"})
    private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Facility facility;
	
	
	double price;
	
}
