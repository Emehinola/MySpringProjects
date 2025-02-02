package com.test.springmvc;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.springmvc.dao.AlienDao;
import com.test.springmvc.models.Alien;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	AlienDao dao;
	
	@ModelAttribute
	public void setHeader(Model model) {
		model.addAttribute("headerTitle", "Aliens");
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/add-alien")
	public String addAlien() {
		return "addAlien";
	}

	
//	@GetMapping("/add1")
//	public String add1(HttpServletRequest req) {
//		final int num1 = Integer.parseInt(req.getParameter("num1"));
//		final int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		final int result = num1 + num2;
//		
//		HttpSession session = req.getSession();
//		session.setAttribute("result", result);
//		
//		return "result.jsp";
//	}
//	
//	@GetMapping("/add2")
//	public String add2(@RequestParam("num1") int i, @RequestParam("num2") int j, HttpSession session) {
//		int result = i+j;
//		session.setAttribute("result", result);
//		return "result.jsp";
//	}
	
	@GetMapping("/add3")
	public ModelAndView add3(@RequestParam("num1") int i, @RequestParam("num2") int j) {
		int result = i+j;
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("result");
		mv.addObject("result", result); // add the result object
		
		return mv;
	}
	
	@GetMapping("/add")
	public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, Model model) {
		int result = i+j;
		
		model.addAttribute("result", result); // add the result object
		
		return "result";
	}
	
	@GetMapping("/show-alien")
	public String showAlien(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
		
		Alien a = new Alien();
		a.setId(id);
		a.setName(name);
		
		model.addAttribute("alien", a); // add the result object
		
		return "alien";
	}
	
	@PostMapping("/show-alien2")
	public String showAlien2(@ModelAttribute Alien alien, Model model) {
		
		model.addAttribute("alien", alien); // add the result object
		
		return "alien";
	}
	
	@GetMapping("get-aliens")
	public String getAliens(Model model) {
		List<Alien> aliens = Arrays.asList(new Alien(101, "Sam"), new Alien(102, "sdkn"));
		model.addAttribute("result", dao.getAliens());
		System.out.println("Hello");
		
		return "showAliens";
	}

}
