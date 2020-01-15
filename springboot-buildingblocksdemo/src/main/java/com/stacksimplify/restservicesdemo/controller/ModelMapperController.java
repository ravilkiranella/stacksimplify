package com.stacksimplify.restservicesdemo.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservicesdemo.dto.UserMmDTO;
import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.service.UserService;

@RestController
@RequestMapping(value="/modelmapper/users")
public class ModelMapperController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@GetMapping("/{id}")
	public UserMmDTO getUserById(@PathVariable @Min(1) Long id) throws UserNotFoundException {
		
			Optional<User> user =  userService.getUserById(id);
		
			if(!user.isPresent()) {
				throw new UserNotFoundException("User not found");
			}
			User usr = user.get();
			UserMmDTO dto = modelMapper.map(usr, UserMmDTO.class); //convert entity to DTO
		return dto;
	}
}
