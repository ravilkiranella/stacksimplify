package com.stacksimplify.restservicesdemo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.entity.Views;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.service.UserService;

@RestController
@Validated
@RequestMapping(value="/jsonview/users")
public class UserJsonViewController {

	
	@Autowired
	private UserService userService;
	
	@JsonView(Views.External.class)
	@GetMapping("/external/{id}")
	public Optional<User> getUserById(@PathVariable @Min(1) Long id) {
		Optional<User> user = null;
		
		try {
			user =  userService.getUserById(id);
		} catch (UserNotFoundException e) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}
	
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public Optional<User> getUserById2(@PathVariable @Min(1) Long id) {
		Optional<User> user = null;
		
		try {
			user =  userService.getUserById(id);
		} catch (UserNotFoundException e) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return user;
	}
}
