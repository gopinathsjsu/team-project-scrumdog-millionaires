package com.cmpe202.app.hotelbooking.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESERVATION", catalog = "HOTEL_APP")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
@Component
public class Reservation {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String reservationId;

	private LocalDate startDate;

	private LocalDate endDate;

	int peopleCount;

	@ManyToOne
	@JsonIgnoreProperties({"hotel","roomType"})
	Room room;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	@JsonIgnoreProperties({"address","roles","points","hotelEmployee"})
	User user;

	int roomCount;
	
	@Enumerated(EnumType.STRING)
	EStatus status;
	
	

}
