package com.group1.SDCapplication.courseadministration.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group1.SDCapplication.courseadministration.services.StudentFileDataUpload;
import com.group1.SDCapplication.courseadministration.services.StudentFileDataValidation;
import com.group1.SDCapplication.courseadministration.services.UserFileDataUpload;
import com.group1.SDCapplication.courseadministration.services.UserFileDataValidation;

@Controller
@RequestMapping("/administercourse")
public class CourseAdministerController {

	@GetMapping("/courseadministerpage/{courseNumber}")
	public String renderCourseAdminPage(@PathVariable("courseNumber") String courseNumber, Model model) {
		model.addAttribute("courseNumber", courseNumber);
		return "course-administer";
	}

	@PostMapping("/addstudents/{courseNumber}")
	public String addStudentsViaCsv(@RequestParam("file") MultipartFile file,
			@PathVariable("courseNumber") String courseNumber, Model model) {

		model.addAttribute("courseNumber", courseNumber);

		// Validation
		UserFileDataValidation ui = new StudentFileDataValidation(file);
		if (!ui.validateFileType()) {
			model.addAttribute("status", false);
			model.addAttribute("message", "Not a .csv file");
			return "upload-status";
		}

		if (!ui.validateFields()) {
			model.addAttribute("status", false);
			model.addAttribute("message",
					"Invalid column name. Columns must be 'banner_id', 'last_name', 'first_name', 'email'");
			return "upload-status";
		}

		if (!ui.validateData()) {
			model.addAttribute("status", false);
			model.addAttribute("message", "Some email(s) or bannerId(s) are invalid");
			return "upload-status";
		}
		
		// Upload and Notify
		StudentFileDataUpload sfd = new StudentFileDataUpload(file);
		sfd.setCourseNumber(courseNumber);
		if (!sfd.uploadData()) {
			model.addAttribute("status", false);
			model.addAttribute("message", "File data upload failed");
			return "upload-status";
		}

		model.addAttribute("status", true);

		return "upload-status";
	}

}
