package com.group1.SDCapplication.admin.controller;

import com.group1.SDCapplication.admin.services.AdminService;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String index(Model model)  {
        List<Courses> courses = new ArrayList<>();
        AdminService service = new AdminService();
        courses = service.getAllCourses();
        model.addAttribute("courses", courses);
        return "/Admin/admin";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<String> id)
    {
        AdminService service = new AdminService();
        List<Instructor> instrutors = service.getAllInstructors();
        if (id.isPresent()) {
            Courses entity = service.getCourseById(id.get());
            model.addAttribute("course", entity);
            model.addAttribute("instructorList",instrutors);
        } else {
            Courses courses = new Courses();
            model.addAttribute("course", courses);
            model.addAttribute("instructorList",instrutors);
        }
        return "/Admin/add-edit-course";
    }

    @RequestMapping(path = "/createCourse", method = RequestMethod.POST)
    public String createOrUpdateCourse(Model model,@ModelAttribute Courses course)
    {
        AdminService service = new AdminService();
        System.out.println(course.getCourseID());
        service.createOrUpdateCourse(course);
        return "/Admin/admin";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteCourseByCourseNumber(Model model, @PathVariable("id") String id)
    {
        AdminService service = new AdminService();
        service.deleteCourseByCourseNumber(id);
        return "/Admin/admin";
    }
}
