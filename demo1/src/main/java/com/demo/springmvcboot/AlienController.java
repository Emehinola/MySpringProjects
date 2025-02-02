package com.demo.springmvcboot;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springmvcboot.models.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@GetMapping(path="aliens", produces={"application/xml"}) // application/xml
	public List<Alien> getAliens() {
		List<Alien> aliens = repo.getAll();
		
		return aliens;
	}
	
	@GetMapping("aliens/{id}")
	public Alien getAlienById(@PathVariable("id") int id) {
		Alien alien = repo.findById(id).orElse(null);
		return alien;
	}
	
	@PostMapping(path="aliens", consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
}
