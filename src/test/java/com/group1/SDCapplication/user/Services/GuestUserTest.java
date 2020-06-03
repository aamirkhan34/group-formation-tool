package com.group1.SDCapplication.user.Services;

import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.CoursesTest;
import com.group1.SDCapplication.user.dao.Course;
import com.group1.SDCapplication.user.dao.CoursesDao;
import com.group1.SDCapplication.user.dao.CoursesDaoMock;
import com.group1.SDCapplication.user.dao.CoursesDaoTest;
import com.group1.SDCapplication.user.services.GuestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GuestUserTest {

    Course coursesDao;

    @BeforeEach
    public void Initialize(){
        coursesDao = new CoursesDaoMock();
    }

    @Test
    public void getCoursesTest(){
        assertNotNull(coursesDao.getCourses());
    }

}
