package com.group1.SDCapplication.user.services;

import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.user.dao.CoursesDao;

import java.util.List;

public class GuestUser {
    public List<Courses> getCourses() {
        List<Courses> allCourses;
        CoursesDao getCourses = new CoursesDao();
        allCourses = getCourses.getCourses();
        return allCourses;
    }
}
