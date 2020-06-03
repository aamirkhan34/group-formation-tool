package com.group1.SDCapplication.courseadministration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/administercourse")
public class CourseAdministerController {

	@GetMapping("/courseadministerpage/{courseNumber}")
	public String renderCourseAdminPage(@PathVariable("courseNumber") String courseNumber, Model model) {
//		System.out.println(courseNumber);
//		String tokenDetails = (String) model.getAttribute("token");
//		System.out.println(tokenDetails.toString());
		// Add students/ins/ta details, course info.
		model.addAttribute("courseNumber", courseNumber);
		return "/Course/course-administer";
	}

	@PostMapping("/addstudents")
	public String addStudentsViaCsv(@RequestParam("file") MultipartFile file, Model model) {
//		System.out.println(model.getAttribute("courseNumber"));
		System.out.println(file);
//		String tokenDetails = (String) model.getAttribute("token");
//		System.out.println(tokenDetails.toString());
		// Add students/ins/ta details, course info.
//		model.addAttribute("courseNumber", courseNumber);
		return "/Course/course-administer";
	}

}
