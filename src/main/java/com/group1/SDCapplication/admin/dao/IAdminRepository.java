package com.group1.SDCapplication.admin.dao;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.Instructor;

import java.util.List;

public interface IAdminRepository {
    public List<Courses> loadAllCourses();

    public boolean createCourse(Courses course);

    List<Courses> findById(String id);

    List<Instructor> getAllInstructors();

    String createOrUpdateCourse  (Courses course);

    String deleteCourseByCourseNumber(Long courseNumber);
}
