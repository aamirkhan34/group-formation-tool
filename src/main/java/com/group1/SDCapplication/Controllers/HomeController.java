package com.group1.SDCapplication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(Model model) {
		return "/Home/index";
	}

	@GetMapping("/Login")
	public String Login(Model model){
		return "/Login/Login";
	}

	@GetMapping("/Signup")
	public String Signup(Model model){
		return "/Signup/Signup";
	}

}
