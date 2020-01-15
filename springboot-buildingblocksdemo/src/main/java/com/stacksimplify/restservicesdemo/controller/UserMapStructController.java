package com.stacksimplify.restservicesdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservicesdemo.dto.UserMsDTO;
import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.mappers.UserMapper;
import com.stacksimplify.restservicesdemo.repository.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDTO> getAllUserDTOs(){
		List<User> list = userRepository.findAll();
		System.out.println("<<<<<<>>>>>>>>>>>" +list.get(0).getEmail());
		return userMapper.usersToUserMsDTOs(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDTO getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		User user = userOptional.get();
		return userMapper.userToUserMsDTO(user);
	}
}
