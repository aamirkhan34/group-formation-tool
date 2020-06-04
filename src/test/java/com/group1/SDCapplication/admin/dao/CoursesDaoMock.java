package com.group1.SDCapplication.admin.dao;

import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.Instructor;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.dao.UserAdd;

import java.util.ArrayList;
import java.util.List;

public class CoursesDaoMock implements IAdminRepository {
    List<Courses> coursesList = new ArrayList<>();

    @Override
    public List<Courses> loadAllCourses() {
        for (int i = 0; i < 4; i++) {
            Courses course = new Courses();
            course.setCourse_id((long) i);
            course.setInstructor_name("2");
            course.setCourseName("Adv SDC");
            course.setCourseNumber("CSCI5308");
            course.setInstructor_number((long) i);
            coursesList.add(course);
        }
        return coursesList;
    }

    @Override
    public List<Courses> findById(String id) {
        loadAllCourses();
        List<Courses> result = new ArrayList<>();
        for (Courses c : coursesList) {
            if (c.getCourse_id().toString().equals(id)) {
                result.add(c);
                return result;
            }
        }
        return null;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return null;
    }

    @Override
    public String createOrUpdateCourse(Courses course) {
        String courseNumber = course.getCourseNumber();
        String courseName = course.getCourseName();
        String instructorName = course.getInstructor_name();
        Long instuctorNumber = course.getInstructor_number();
        if (courseNumber == "CSCI7888" && courseName == "Adv SDC" && instructorName == "Rob" && instuctorNumber == 1) {
            return "Success";
        }
        return "Failed";
    }


    @Override
    public String deleteCourseByCourseNumber(Long courseNumber) {
        loadAllCourses();
        for (Courses c : coursesList) {
            if (c.getCourse_id().equals(courseNumber)) {
                coursesList.remove(c);
                return "Course deleted";
            }
        }
        return "Error while deleting";
    }
}
