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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity @JsonAutoDetect
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;

    @Column(name = "address1")
    @NotEmpty(message = "*Please enter your address")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "pincode")
    private String pincode;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="city_id",referencedColumnName = "city_id")
    private City city;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;
}

