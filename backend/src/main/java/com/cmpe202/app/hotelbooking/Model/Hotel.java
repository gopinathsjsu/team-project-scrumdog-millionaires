package com.cmpe202.app.hotelbooking.Model;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity @JsonAutoDetect

@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private int id;

    @Column(name = "hotel_name")
    private String hotel_name;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="hoteltype_id",referencedColumnName = "hoteltype_id")
    private HotelType hotelType;

    @Column(name="hotelWheelchairAcc")
    private boolean hotelWheelchairAcc;

    @Column(name="hotelCovidCertified")
    private boolean hotelCovidCertified;

    @Column(name="hotelPetsAllowed")
    private boolean hotelPetsAllowed;

    @NotEmpty(message = "*Please provide a phone number")
    @Column(name="hotelPhone1")
    private String hotelPhone1;

    @Column(name="hotelPhone2")
    private String hotelPhone2;

    @NotEmpty(message = "*Please provide email id")
    @Column(name="hotelEmail")
    private String hotelEmail;

    @NotEmpty(message = "*Please provide image path")
    @Column(name="hotelImagePath")
    private String hotelImagePath;


    @Column(name = "hotelCreatedOn")
    @CreationTimestamp
    private Timestamp hotelCreatedOn;

    @Column(name="hotelModifiedOn",columnDefinition = "now()")
    @UpdateTimestamp
    private Timestamp  hotelModifiedOn;

    @Column(name="hotelActive")
    private boolean hotelActive;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="ADDRESS_ID",referencedColumnName = "address_id")
    private Address address;




}