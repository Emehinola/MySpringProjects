package com.demo.firstproject;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Alien {
	
	@Autowired
	Laptop laptop;
	public void code() {
		laptop.compile(); // compile with laptop
	}
}
