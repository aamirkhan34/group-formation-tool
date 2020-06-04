package com.group1.SDCapplication.models;

public class Courses {
    private String courseNumber;
    private String courseName;
    private String instructor_name;
    private Long intsructor_number;

    public Long getIntsructor_number() {
        return intsructor_number;
    }

    public void setIntsructor_number(Long intsructor_number) {
        this.intsructor_number = intsructor_number;
    }

    public Courses() {

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

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }
}
