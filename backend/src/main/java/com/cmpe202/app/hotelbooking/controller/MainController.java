package com.cmpe202.app.hotelbooking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe202.app.hotelbooking.model.Address;
import com.cmpe202.app.hotelbooking.model.City;
import com.cmpe202.app.hotelbooking.model.State;
import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.AddressRepository;
import com.cmpe202.app.hotelbooking.repository.CityRepository;
import com.cmpe202.app.hotelbooking.repository.StateRepository;
import com.cmpe202.app.hotelbooking.service.UserService;

@RestController
public class MainController {
	
	@Autowired
    private UserService userService;
	
	@Autowired 
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	/*
	 * @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET) public
	 * ModelAndView login(){ ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("login"); return modelAndView; }
	 */
	

	@RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
	
	@ResponseStatus(
		    value = HttpStatus.UNPROCESSABLE_ENTITY, 
		    reason = "User with email already exists"
		)
		public class UserAlreadyRegistered 
		        extends RuntimeException {
		 
		    public UserAlreadyRegistered(Throwable t) {
		        super(t);
		    }

			public UserAlreadyRegistered(String string) {
				// TODO Auto-generated constructor stub
			}
		}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST,
			consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewUser(@RequestBody @Valid User user,
			
			BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("In register");
        System.out.println(user.getFirstName());
        System.out.println(user.getEmail());
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
           // modelAndView.setViewName("registration");
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
        	try {
        	Address address=user.getAddress();
        	City city=address.getCity();
        	State state=city.getState();
            userService.saveUser(user);
            addressRepository.save(address);
            cityRepository.save(city);
            stateRepository.save(state);
        	}
        	catch(javax.validation.ConstraintViolationException e) {
            	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        	}
			/*
			 * modelAndView.addObject("successMessage",
			 * "User has been registered successfully"); modelAndView.addObject("user", new
			 * User()); modelAndView.setViewName("registration");
			 */
            return "Success";

        }
        //return modelAndView;
    }


}
