package com.demo.springmvcboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public String getAliens() {
		return "home.jsp";
	}
}
