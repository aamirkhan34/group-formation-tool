package com.group1.SDCapplication.models;

public class Courses {
    private String courseID;
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

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
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
