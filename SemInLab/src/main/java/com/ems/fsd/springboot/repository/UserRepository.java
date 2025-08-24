package com.ems.fsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.fsd.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
