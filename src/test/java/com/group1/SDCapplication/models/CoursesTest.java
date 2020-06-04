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
    public void getInstructornumberTest(){
        Courses courses = new Courses();
        courses.setInstructor_number((long) 1);
        assertEquals(1,courses.getInstructor_number());
    }

    @Test
    public void getInstructornameTest(){
        Courses courses = new Courses();
        courses.setInstructor_name("instructor");
        assertTrue(courses.getInstructor_name().equals("instructor"));
    }
    @Test
    public void getCourseIdTest(){
        Courses courses = new Courses();
        courses.setCourse_id((long) 1);
        assertEquals(1,courses.getCourse_id());
    }

}
