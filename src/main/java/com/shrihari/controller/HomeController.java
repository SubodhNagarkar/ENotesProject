package com.shrihari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shrihari.entity.User;
import com.shrihari.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		boolean f = userService.existEmailCheck(user.getEmail());
		if(f) {
			session.setAttribute("msg", "Email Already Exist !!");
		}
		else {
			User userSaved = userService.saveUser(user);
			if (userSaved != null) {
				session.setAttribute("msg", "Sucessufully Saved !! Congratualtions !!");
			}
			else {
				session.setAttribute("msg", " Error OCcured");
			}
		}
		
		
		return "redirect:/register";
	}
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	
}
