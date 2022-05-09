package com.cmpe202.app.hotelbooking.controller;

import java.util.ArrayList;
import java.util.List;

import com.cmpe202.app.hotelbooking.model.Role;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe202.app.hotelbooking.POJOs.MessageResponse;
import com.cmpe202.app.hotelbooking.POJOs.RegisterUserRequest;
import com.cmpe202.app.hotelbooking.model.Address;
import com.cmpe202.app.hotelbooking.model.CustomerPoints;
import com.cmpe202.app.hotelbooking.model.Hotel;
import com.cmpe202.app.hotelbooking.model.HotelEmployee;
import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.HotelEmployeeRepository;
import com.cmpe202.app.hotelbooking.repository.HotelRepository;
import com.cmpe202.app.hotelbooking.repository.RoleRepository;
import com.cmpe202.app.hotelbooking.repository.UserRepository;
import com.cmpe202.app.hotelbooking.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HotelEmployeeRepository hotelEmployeeRespository;
	
	@Autowired
	private RoleRepository roleRepository;

	/*
	 * @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET) public
	 * ModelAndView login(){ ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("login"); return modelAndView; }
	 */

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<?> createNewUser(@Valid @RequestBody RegisterUserRequest userRequest)
	{
		
		
		User userExists = userService.findUserByEmail(userRequest.getEmail());
		if (userExists != null) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: a user with this email already exists"));

		}
		if (userRequest.getRole() == null || userRequest.getRole().isEmpty() 
				|| !(userRequest.getRole().equals("EMPLOYEE") || userRequest.getRole().equals("CUSTOMER"))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: no role specified for the user"));

		}
		if (userRequest.getRole().equals("CUSTOMER") && userRequest.getHotelId()!=null && !userRequest.getHotelId().isEmpty()) {
			return ResponseEntity.badRequest().body(
					new MessageResponse("Error: hotel parameter can be passed only for users with EMPLOYEE role"));

		}
		if(userRequest.getRole().equals("EMPLOYEE") && (userRequest.getHotelId()==null || userRequest.getHotelId().isEmpty())){
			return ResponseEntity.badRequest().body(
			new MessageResponse("Error: hotel parameter is empty"));

		}
		

		User user = new User();
		user.setActive(true);
		user.setEmail(userRequest.getEmail());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setPassword(userRequest.getPassword());

		String reqRole=userRequest.getRole();
		List<Role> roles=new ArrayList<>();
		
		if (reqRole.equals("CUSTOMER")) {
			CustomerPoints cPoints = new CustomerPoints();
			cPoints.setPoints(0);
			user.setPoints(cPoints);
			roles.add(roleRepository.findByRole(reqRole));
			
		} else if (reqRole.equals("EMPLOYEE")) {
			Hotel hotel = hotelRepository.getById(userRequest.getHotelId());
			if (hotel == null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid hotel ID"));

			}
			roles.add(roleRepository.findByRole(reqRole));
			
		}
		user.setRoles(roles);
		Address address=new Address();
		address.setCity(userRequest.getCity());
		address.setState(userRequest.getState());
		address.setNumber(userRequest.getNumber());
		address.setStreet(userRequest.getStreet());
		address.setZip(userRequest.getZip());
		user.setAddress(address);
		User newUser = userService.createUser(user);
		if (reqRole.equals("EMPLOYEE")) {
			Hotel hotel = hotelRepository.getById(userRequest.getHotelId());
			HotelEmployee hotelemployee = new HotelEmployee();
			hotelemployee.setHotel(hotel);
			hotelemployee.setUser(newUser);
			hotelEmployeeRespository.save(hotelemployee);
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	@RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> updateUser(@Valid @RequestBody RegisterUserRequest userRequest, @PathVariable("id") String id) {
		User userExists = userService.findUserByID(Integer.parseInt(id))
				.orElseThrow(() -> new EntityNotFoundException("Invalid Team ID"));
		if (userExists == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: user does not exist"));
		} else {
			User newUser=new User();
			newUser.setPassword(userRequest.getPassword());
			newUser.setFirstName(userRequest.getFirstName());
			newUser.setLastName(userRequest.getLastName());
			newUser.setEmail(userRequest.getEmail());
			Address adress=new Address();
			adress.setCity(userRequest.getCity());
			adress.setStreet(userRequest.getStreet());
			adress.setNumber(userRequest.getNumber());
			adress.setZip(userRequest.getZip());
			adress.setState(userRequest.getState());
			adress.setCity(userRequest.getCity());
			newUser.setAddress(adress);
			userService.updateUser(newUser, Integer.parseInt(id));
			return ResponseEntity.ok(new MessageResponse("User updated successfully!"));

		}
	}



	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> updatePoints(@PathVariable("id") String id, @RequestParam("points") String points) {
		try {
			userService.updatePoints(Integer.parseInt(id), Integer.parseInt(points));

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
		}
		return ResponseEntity.ok(new MessageResponse("points updated successfully!"));
	}
}
