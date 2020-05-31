package com.group1.SDCapplication.models;

public class Courses {
    private String courseNumber;
    private String courseName;
    private String instructor;

    public Courses(){

    }

    public Courses(String courseNumber, String courseName, String instructor) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.instructor = instructor;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
