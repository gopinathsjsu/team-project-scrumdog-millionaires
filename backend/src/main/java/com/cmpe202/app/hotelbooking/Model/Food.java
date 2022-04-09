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

@Table(name = "Food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "foodId")
    private int foodId;

    @NotEmpty(message = "*Please provide Food Name")
    @Column(name = "foodName")
    private String foodName;

    @Column(name = "foodEssc")
    private String foodEssc;

}