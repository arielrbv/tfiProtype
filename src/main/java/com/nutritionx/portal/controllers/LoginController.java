package com.nutritionx.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("msg", "holaMundo");
		return "login";
	}

}
