package com.stacksimplify.restservicesdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.exception.UserExistsException;
import com.stacksimplify.restservicesdemo.exception.UserNameNotFoundException;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.service.UserService;

@RestController
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers =  new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exists");
		}
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable @Min(1) Long id) {
		Optional<User> user = null;
		
		try {
			user =  userService.getUserById(id);
		} catch (UserNotFoundException e) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		System.out.println("Put method called");
		User userObj = null;
		try {
			userObj = userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return userObj;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		System.out.println("Put method called");
		User userObj = null;
		try {
			 userService.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	@GetMapping("/users/byusername/{username}")
	public User findByUsername(@PathVariable String username){
		return userService.findByUsername(username);
	}
	
	@GetMapping("/users/getbyusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.findByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username "+username+" not found in User repository");
		return user;
	}
}
