package com.ems.fsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.fsd.springboot.model.User;

import com.ems.fsd.springboot.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

   
	@Autowired
	private UserService service;

   
	@GetMapping("/")
	public String home() {
		return "User Home page";
	}
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User u) {
		return service.addUser(u);
	}
	
	@GetMapping("/viewAllUsers")
	public List<User> viewAllUsers(){
		
		return service.viewAllUser();
	}
	
	@DeleteMapping("deleteUser")
	public String deleteUser(@RequestParam int id) {
		return service.deleteUser(id);
	}
	
	@PutMapping("updateUser")
	public String updateUser(@RequestBody User u) {
		
		return service.updateUser(u);
	}
	
	

}
