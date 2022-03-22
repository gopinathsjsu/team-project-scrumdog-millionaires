package com.cmpe202.app.hotelbooking.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonAutoDetect
@Table(name = "state")
public class State {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "state_id")
	private int id;
	
	@Column(name = "state_name")
	@NotEmpty(message = "*Please enter your state")
	private String stateName;
	
	@Column(name = "country")
	@NotEmpty(message = "*Please enter your country")
	private String country;
	
	@OneToOne(mappedBy = "state", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private City city;

}
