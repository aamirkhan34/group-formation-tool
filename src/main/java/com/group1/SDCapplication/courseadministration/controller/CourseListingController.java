package com.group1.SDCapplication.courseadministration.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group1.SDCapplication.courseadministration.dao.InstructorCourseDao;
import com.group1.SDCapplication.courseadministration.dao.StudentCourseDao;
import com.group1.SDCapplication.courseadministration.dao.TaCourseDao;
import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.services.UserValidation;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.user.dao.Course;
import com.group1.SDCapplication.user.services.GuestUser;

@Controller
@RequestMapping("/course")
public class CourseListingController {

	@GetMapping("/courselisting")
	public String courseListing(Model model) {
		String tokenDetails = (String) model.getAttribute("token");
		System.out.println(tokenDetails);
//		List<String> userRoles = new ArrayList<>();
		String finalRole = "instructor";
		
//		UserValidation userValidation = new UserValidation();
//		userRoles = userValidation.getUserRoles(userCredentials);
//		
//		for (String role : userRoles) {
//			finalRole = role;
//		}
//		
//		model.addAttribute("role", finalRole);
//		System.out.println(finalRole.toString());
//		System.out.println(userCredentials.toString());
		model.addAttribute("role", finalRole);
		
		if (finalRole.equals("student")) {
			Course clist = new StudentCourseDao();
			model.addAttribute("courses", clist.getCourses());
		} else if (finalRole.equals("instructor")) {
			Course clist = new InstructorCourseDao();
			model.addAttribute("courses", clist.getCourses());
		} else
		{
			Course clist = new TaCourseDao();
			model.addAttribute("courses", clist.getCourses());
		}
		
		return "/Course/course-listing";
	}

}
