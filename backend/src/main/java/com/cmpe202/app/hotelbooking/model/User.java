package com.cmpe202.app.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", catalog = "hotel_app")
@JsonAutoDetect
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_EMAIL", unique = true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@NotEmpty(message = "*Please provide your first name name")
	@Column(name = "USER_FIRST_NAME")
	private String firstName;

	@Column(name = "USER_MIDDLE_NAME")
	private String middleName;

	@NotEmpty(message = "*Please provide your last name")
	@Column(name = "USER_LAST_NAME")
	private String lastName;

	@NotEmpty(message = "*Please provide a password")
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACTIVE")
	private boolean active;

	@Embedded
	private Address address;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "HOTEL_EMP_ID", referencedColumnName = "HOTEL_EMP_ID")
	@JsonIgnoreProperties("hotel")
	private HotelEmployee hotelEmployee;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "POINTS_ID", referencedColumnName = "POINTS_ID")
	private CustomerPoints points;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

}
