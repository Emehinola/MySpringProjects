package com.demo.springmvcboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}
