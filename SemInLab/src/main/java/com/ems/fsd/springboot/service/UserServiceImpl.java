package com.ems.fsd.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.fsd.springboot.model.User;
import com.ems.fsd.springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public String addUser(User u) {
		Optional<User> object = repository.findById(u.getId());
		String msg;
		if (object.isPresent()) {
			msg = "User Already there exist !!";
		} else {
			repository.save(u);
			msg = "User Added Successfully";
		}
		return msg;

	}

	@Override
	public String updateUser(User u) {
		Optional<User> object = repository.findById(u.getId());
		String msg;
		if (object.isPresent()) {
			User u1 = object.get();
			u1.setName(u.getName());
			u1.setGender(u.getGender());
			u1.setEmail(u.getEmail());
			u1.setContact(u.getContact());
			msg = "User Updated Successfully!!";
		} else {

			msg = "User Not Found ";
		}
		return msg;
	}

	@Override
	public String deleteUser(int id) {
		Optional<User> object = repository.findById(id);
		String msg;
		if(object.isPresent()) {
			User u = object.get();
			repository.delete(u);
			msg ="User Deleted Successfully ";
		}
		else {
			msg ="User not found to delete";
		}
		
		return msg;
	}


	@Override
	public List<User> viewAllUser() {
		
		return repository.findAll();
	}

}
