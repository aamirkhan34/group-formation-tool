package com.group1.SDCapplication.models;

public class Courses {
    private String courseNumber;
    private String courseName;
    private String instructor_name;
    private Long instructor_number;
    private Long course_id;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getInstructor_number() {
        return instructor_number;
    }

    public void setInstructor_number(Long instructor_number) {
        this.instructor_number = instructor_number;
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
