package com.group1.SDCapplication.user.dao;

import com.group1.SDCapplication.models.Courses;

import java.util.ArrayList;
import java.util.List;

public class CoursesDaoMock implements Course {

    @Override
    public List<Courses> getCourses() {

        List<Courses> coursesList = new ArrayList<>();
        for (int i = 0; i<2; i++){
            Courses course = new Courses();
            course.setInstructor_name("2");
            course.setCourseName("Adv SDC");
            course.setCourseNumber("CSCI5308");
            coursesList.add(course);
        }
        return coursesList;
    }
}
