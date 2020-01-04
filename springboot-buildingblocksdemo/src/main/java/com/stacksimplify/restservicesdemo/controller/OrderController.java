package com.stacksimplify.restservicesdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservicesdemo.entity.Order;
import com.stacksimplify.restservicesdemo.entity.User;
import com.stacksimplify.restservicesdemo.exception.UserNotFoundException;
import com.stacksimplify.restservicesdemo.repository.OrderRepository;
import com.stacksimplify.restservicesdemo.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private OrderRepository orderRespository;
	
	//get all orders for a user
	@GetMapping("/{userid}/orders")
	private List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> user = userRespository.findById(userid);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User is not found");
		}
		return user.get().getOrders();
	}
	
	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException{
		Optional<User> optionalUser = userRespository.findById(userid);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		User user = optionalUser.get();
		order.setUser(user);
		orderRespository.save(order);
	}

}
