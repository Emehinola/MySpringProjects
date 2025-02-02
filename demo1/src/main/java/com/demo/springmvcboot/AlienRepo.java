package com.demo.springmvcboot;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.springmvcboot.models.Alien;


public interface AlienRepo extends JpaRepository<Alien, Integer> {
	
	List<Alien> findByName(String name); // Query DSL
	
	@Query("from Alien")
	List<Alien> getAll();
}
