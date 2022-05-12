package com.cmpe202.app.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROOM", catalog = "hotel_app")
@JsonAutoDetect
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
public class Room {
	
	@EmbeddedId
	private HotelRoomTypeId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("hotelId")
	@JsonIgnoreProperties({"hotelRoomTypes","employees"})
    private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("typeId")
	@JsonIgnoreProperties({"rooms"})
    private RoomType roomType;
	
	@Column(name="BASE_PRICE")
	private double basePrice;
	
	@Column(name="WEEKEND_SURGE")
	private double weekendSurge;
	
	@Column(name="HOLIDAY_SURGE")
	private double holidaySurge;
	
	
	@Column(name="ROOM_COUNT")
	private int roomCount;

}
