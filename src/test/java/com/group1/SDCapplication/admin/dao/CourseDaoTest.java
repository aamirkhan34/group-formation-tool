package com.group1.SDCapplication.admin.dao;

import com.group1.SDCapplication.models.Courses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDaoTest {
    CoursesDaoMock mockData;
    IAdminRepository coursesDao;

    @BeforeEach
    public void Initialize() {
        mockData = new CoursesDaoMock();
    }

    @Test
    public void loadAllCoursesTest() {
        coursesDao = new CoursesDaoMock();
        assertNotNull(coursesDao.loadAllCourses(), () -> "error while getting courses from DB");
    }

    @Test
    public void createOrUpdateCourseTest() {
        coursesDao = new CoursesDaoMock();
        Courses course = new Courses();
        course.setInstructor_name("Rob");
        course.setCourseName("Adv SDC");
        course.setCourseNumber("CSCI7888");
        course.setInstructor_number((long) 1);
        assertTrue(coursesDao.createOrUpdateCourse(course).equals("Success"), () -> "Failed");
    }

    @Test
    public void getCourseByIDTest() {
        coursesDao = new CoursesDaoMock();
        List<Courses> lstCourse = coursesDao.findById("2");
        assertNotNull(lstCourse);
    }

    @Test
    public void deleteCourseTest() {
        coursesDao = new CoursesDaoMock();
        String result = coursesDao.deleteCourseByCourseNumber(Long.valueOf(2));
        assertNotNull(result);
        assertTrue(coursesDao.deleteCourseByCourseNumber(Long.valueOf(2)).equals("Course deleted"), () -> "Error while deleting");
    }
}
