package com.group1.SDCapplication.user.services;

import com.group1.SDCapplication.admin.services.AdminService;
import com.group1.SDCapplication.models.Courses;

import java.util.List;

public class GuestUser {
    public List<Courses> getCourses() {
        List<Courses> allCourses;
        AdminService servcie= new AdminService();
        allCourses = servcie.getAllCourses();
        return allCourses;
    }
}
