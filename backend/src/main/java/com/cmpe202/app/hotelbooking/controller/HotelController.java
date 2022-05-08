package com.cmpe202.app.hotelbooking.controller;



import com.cmpe202.app.hotelbooking.exception.ResourceNotFoundException;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.Address;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAllHotels(){
        return  hotelRepository.findAll();
    }

    // build create hotel REST API
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // build get hotel by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable  String id){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel does not exist with id:" + id));
        return ResponseEntity.ok(hotel);
    }

    // build update hotel REST API
    @PutMapping("{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id,@RequestBody Hotel hotelDetails) {
        Hotel updateHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id: " + id));

        updateHotel.setHotelName(hotelDetails.getHotelName());
        updateHotel.setHotelType(hotelDetails.getHotelType());
        updateHotel.setEmailId(hotelDetails.getEmailId());
        updateHotel.setDescription(hotelDetails.getDescription());
        updateHotel.setPhoneNum(hotelDetails.getPhoneNum());
        updateHotel.setAddress(hotelDetails.getAddress());

        hotelRepository.save(updateHotel);

        return ResponseEntity.ok(updateHotel);
    }

    // build delete hotel REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteHotel(@PathVariable String id){

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id: " + id));

        hotelRepository.delete(hotel);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}

