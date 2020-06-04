package com.group1.SDCapplication.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoursesTest {

    @Test
    public void getCourseNumberTest(){
        Courses courses = new Courses();
        courses.setCourseNumber("1");
        assertTrue(courses.getCourseNumber().equals("1"));
    }

    @Test
    public void getCourseNameTest(){
        Courses courses = new Courses();
        courses.setCourseName("SDC");
        assertTrue(courses.getCourseName().equals("SDC"));
    }

    @Test
    public void getInstructornumber(){
        Courses courses = new Courses();
        courses.setIntsructor_number((long) 1);
        assertTrue(courses.getIntsructor_number().equals("1"));
    }

}
