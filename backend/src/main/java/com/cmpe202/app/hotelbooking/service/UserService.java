package com.cmpe202.app.hotelbooking.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe202.app.hotelbooking.model.Address;
import com.cmpe202.app.hotelbooking.model.CustomerPoints;
import com.cmpe202.app.hotelbooking.model.Role;

import com.cmpe202.app.hotelbooking.model.User;
import com.cmpe202.app.hotelbooking.repository.RoleRepository;
import com.cmpe202.app.hotelbooking.repository.UserRepository;


@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByEmail(userName);
    }
    
    public Optional<User> findUserByID(Integer id) {
    	return userRepository.findById(id);
    }
    
    
    
    public User createUser(User user) {
    	 System.out.println("In create user" +user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);

      /*  List<Role> roles=user.getRoles();
        Role role=roles.get(0);
        if(role.getRole().equals("CUSTOMER")) {
        	user.setHotelEmployee(null);
        	CustomerPoints cpoints=new CustomerPoints();
        	cpoints.setPoints(0);
        	user.setPoints(cpoints);
        }else if(role.getRole().equals("EMPLOYEE")) {
        	user.setPoints(null);
        }
        Role userRole = roleRepository.findByRole(role.getRole());
        user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));*/
        
        return userRepository.save(user);
    }
    
    public void updateUser(User user,Integer id) throws EntityNotFoundException {
    	User curUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Invalid Team ID"));
    	curUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	curUser.setEmail(user.getEmail());
    	curUser.setFirstName(user.getFirstName());
    	curUser.setLastName(user.getLastName());
    	curUser.setMiddleName(user.getMiddleName());
    	Address curAddress=curUser.getAddress();
    	Address newAddress=user.getAddress();
    	if(newAddress!=null) {
    	curAddress.setNumber(newAddress.getNumber());
    	curAddress.setStreet(newAddress.getState());
    	curAddress.setCity(newAddress.getCity());
    	curAddress.setZip(newAddress.getZip());
    	
    	curUser.setAddress(newAddress);
    	
    	}
    	userRepository.save(curUser);
    }
    
    public void deleteUser(Integer userId) throws EntityNotFoundException {
 
			User userExists = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Invalid Team ID"));
			userRepository.delete(userExists);
		
    }
    
    public User getUser(Integer userId) throws EntityNotFoundException{
		User userExists = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Invalid Team ID"));
		return userExists;
    }
    
    
	public void updatePoints(Integer userId,Integer points) throws EntityNotFoundException{
		User userExists = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Not found"));
		CustomerPoints curPoints= userExists.getPoints();
		curPoints.setPoints(points);
		userExists.setPoints(curPoints);
		userRepository.save(userExists);
	}

}
