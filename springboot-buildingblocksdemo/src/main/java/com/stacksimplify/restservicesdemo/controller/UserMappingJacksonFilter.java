package com.stacksimplify.restservicesdemo.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.service.UserService;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonFilter {


	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable @Min(1) Long id) {
		Optional<User> userOptional = null;
		MappingJacksonValue mapper;
		try {
			
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			userOptional =  userService.getUserById(id);
			User user = userOptional.get();
			
			 mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
		} catch (UserNotFoundException e) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return mapper;
	}
	
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable @Min(1) Long id, @RequestParam Set<String> fields) {
		Optional<User> userOptional = null;
		MappingJacksonValue mapper;
		try {
			
			FilterProvider filterProvider = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			userOptional =  userService.getUserById(id);
			User user = userOptional.get();
			
			 mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			
		} catch (UserNotFoundException e) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return mapper;
	}
}
