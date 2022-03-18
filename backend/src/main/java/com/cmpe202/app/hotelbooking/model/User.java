package com.cmpe202.app.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
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
@Inheritance
@DiscriminatorColumn(name="user_type")
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	 @Column(name = "user_email")
	 @Email(message = "*Please provide a valid Email")
	 @NotEmpty(message = "*Please provide an email")
	 private String email;
	 
	 @NotEmpty(message = "*Please provide a user name")
	 @Column(name = "user_FirstName")
	 private String firstName;
	 
	 @Column(name = "user_MiddleName")
	 private String middleName;
	 
	 @NotEmpty(message = "*Please provide a user name")
	 @Column(name = "user_LastName")
	 private String lastName;
	 
	 @NotEmpty(message = "*Please provide a password")
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "user_created_on")
	 private Date userCreatedOn;
	 
	 @Column(name = "user_modified_on")
	 private Date userModifiedOn;
	 
	 @Column(name = "user_end_date")
	 private Date userEndDate;
	 
	 @Column(name="active")
	 private boolean active;
	 
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="address_id",referencedColumnName = "address_id")
	private Address address;
	
	 @ManyToMany(cascade = CascadeType.MERGE)
	  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles;
	 
}
