package com.ems.fsd.springboot.service;

import java.util.List;

import com.ems.fsd.springboot.model.User;

public interface UserService {
	
	public String addUser(User u);
	public String updateUser(User u);
	public String deleteUser(int id);
	
	public List<User> viewAllUser();
	

}
