package com.stacksimplify.restservicesdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservicesdemo.model.UserDetails;

@RestController
public class HelloWorldController {

	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("helloWorld1")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Kalyan", "Reddy", "Hyderabad");
	}
}
