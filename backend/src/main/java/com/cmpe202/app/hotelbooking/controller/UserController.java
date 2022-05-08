package com.cmpe202.app.hotelbooking.controller;


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
import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.UserRepository;
import com.cmpe202.app.hotelbooking.service.UserService;

@RestController
public class UserController {
	
	@Autowired
    private UserService userService;
		
	/*
	 * @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET) public
	 * ModelAndView login(){ ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("login"); return modelAndView; }
	 */
	

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	public ResponseEntity<?> createNewUser(@RequestBody @Valid User user) {
     
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: a user with this email already exists"));

        }
        if(user.getRoles()==null||user.getRoles().isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: no role specified for the user"));

        }
        userService.createUser(user);
        
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
	
	@RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	public ResponseEntity<?> updateUser(@RequestBody @Valid User user,
			@PathVariable("id") String id) {
		User userExists = userService.findUserByID(Integer.parseInt(id)).orElseThrow(() -> new EntityNotFoundException("Invalid Team ID"));
		 if (userExists == null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: user does not exist"));
		 }else {
			 userService.updateUser(user,Integer.parseInt(id));
				return ResponseEntity.ok(new MessageResponse("User updated successfully!"));

		 }
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	public void deleteUser(@PathVariable("id") String id) {
		try {
			userService.deleteUser(Integer.parseInt(id));
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	public User getUser(@PathVariable("id") String id) {
		try {
			User user=userService.getUser(Integer.parseInt(id));
			return user;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public void updatePoints(@PathVariable("id") String id,
			 @RequestParam("points") String points) {
		try {
			userService.updatePoints(Integer.parseInt(id), Integer.parseInt(points));
			
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
