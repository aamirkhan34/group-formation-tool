package com.group1.SDCapplication.user.Services;

import com.group1.SDCapplication.models.CoursesTest;
import com.group1.SDCapplication.user.dao.CoursesDaoTest;

import java.util.List;

public class GuestUserTest {
    public List<CoursesTest> getCoursesTest(){
        List<CoursesTest> allCoursesTest;
        CoursesDaoTest coursesDaoTest = new CoursesDaoTest();
        allCoursesTest = coursesDaoTest.getCoursesTest();
        return allCoursesTest;
    }

}
