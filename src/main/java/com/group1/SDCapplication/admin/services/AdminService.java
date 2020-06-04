package com.group1.SDCapplication.admin.services;

import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.admin.dao.CourseDao;
import com.group1.SDCapplication.models.Instructor;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    CourseDao adminService = new CourseDao();
    public List<Courses> getAllCourses() {
        CourseDao getCourses = new CourseDao();
        List<Courses> result = getCourses.loadAllCourses();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Courses>();
        }
    }

    public Courses getCourseById(String id) {
        CourseDao getCourse = new CourseDao();
        List<Courses> course = getCourse.findById(id);

        if(course.size()==1) {
            return course.get(0);
        } else {
            return null;
            //throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> result = adminService.getAllInstructors();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Instructor>();
        }
    }

    public String createOrUpdateCourse(Courses course) {
        String result = adminService.createOrUpdateCourse(course);
        return result;
    }

    public String deleteCourseByCourseNumber(Long courseNumber) {
        String result = adminService.deleteCourseByCourseNumber(courseNumber);
        return result;
    }
}
