package com.stacksimplify.restservicesdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.exception.UserExistsException;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser != null) throw new UserExistsException("User already exists with given user name");
		return userRepository.save(user);
	}
	
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		 Optional<User> user = userRepository.findById(id); 
		 if(!user.isPresent()) {
			 throw new UserNotFoundException("User not found in user respository");
		 }
		return user; 
	} 
	
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		 Optional<User> optionalUser = userRepository.findById(id); 
		 if(!optionalUser.isPresent()) {
			 throw new UserNotFoundException("User not found in user respository, Provide correct user id");
		 }
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) throws UserNotFoundException {
		 Optional<User> optionalUser = userRepository.findById(id); 
		 if(!optionalUser.isPresent()) {
			 throw new UserNotFoundException("User not found in user respository, Provide correct user id");
		 }
		 userRepository.deleteById(id);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
