package com.cmpe202.app.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

	enum hotelTypeEnum {
		motel, hotel, BreadAndBreakfast, fiveStar
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	// add description and phone num

	@Column(name = "hotel_name")
	private String hotelName;

	@Column(name = "hotel_type")
	private hotelTypeEnum hotelType;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "description")
	private String description;

	@Column(name = "phoneNum")
	private String phoneNum;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"hotel","user"})
	private Set<HotelEmployee> employees;
	
	@OneToMany(
	        mappedBy = "hotel",
	        cascade = CascadeType.MERGE,
	        orphanRemoval = true
	    )
	@JsonIgnoreProperties({"hotel","roomType"})
    private List<Room> hotelRoomTypes;
}