package com.group1.SDCapplication.home.controller;


import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.login.dao.CURD_Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(Model model)  {
		User u_ret = new User();
		List<User> user_records = null;
		CURD_Operation curd = new CURD_Operation();
		try {
			u_ret = curd.DEv_Connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<User> user_records = (List<User>) u_ret;
		model.addAttribute("Userlist", u_ret);
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
