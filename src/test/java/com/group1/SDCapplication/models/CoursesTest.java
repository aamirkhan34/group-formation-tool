package com.group1.SDCapplication.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoursesTest {
//
//    private String courseNumberTest;
//    private String courseNameTest;
//    private String instructorTest;
//
//    public CoursesTest(){
//
//    }
//
//   public CoursesTest(String courseNumberTest, String courseNameTest, String instructorTest) {
//        this.courseNumberTest = courseNumberTest;
//        this.courseNameTest = courseNameTest;
//        this.instructorTest = instructorTest;
//    }
//
//    public void setCourseNumberTest(String courseNumberTest) {
//        this.courseNumberTest = courseNumberTest;
//    }
//
//    public void setCourseNameTest(String courseNameTest) {
//        this.courseNameTest = courseNameTest;
//    }
//
//    public void setInstructorTest(String instructorTest) {
//        this.instructorTest = instructorTest;
//    }
//
//    public String getInstructorTest() {
//        return instructorTest;
//    }

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
