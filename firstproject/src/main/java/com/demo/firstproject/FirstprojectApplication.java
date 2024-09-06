package com.demo.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;

@SpringBootApplication
public class FirstprojectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstprojectApplication.class, args);
		
		Alien obj = context.getBean(Alien.class);
		obj.code();
	}

}
