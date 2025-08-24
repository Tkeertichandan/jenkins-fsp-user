package com.ems.fsd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SemInLabApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SemInLabApplication.class, args);
		System.out.println("Project is Runnung");
	}

}
