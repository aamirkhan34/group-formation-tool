package com.group1.SDCapplication.courseadministration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group1.SDCapplication.courseadministration.services.StudentFileDataValidation;
import com.group1.SDCapplication.courseadministration.services.UserFileDataValidation;

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

	@PostMapping("/addstudents/{courseNumber}")
	public String addStudentsViaCsv(@RequestParam("file") MultipartFile file,
			@PathVariable("courseNumber") String courseNumber, Model model) {
		// TODO: Get user email
		System.out.println(courseNumber);
		System.out.println(file);
		
		model.addAttribute("courseNumber", courseNumber);
		
		// Validate, parse and upload CSV
//		UserFileDataValidation ui = new StudentFileDataValidation(file);
//		if (ui.validateFile() == false) {
//			model.addAttribute("message", "An error occurred while processing the CSV file.");
//            model.addAttribute("status", false);
//		}
		
		model.addAttribute("status", true);
		
		return "/Course/upload-status";
	}

}
