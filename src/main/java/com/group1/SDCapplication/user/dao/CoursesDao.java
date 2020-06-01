package com.group1.SDCapplication.user.dao;

import com.group1.SDCapplication.datasource.DevDatabase;

import com.group1.SDCapplication.models.Courses;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursesDao implements Course{

    @Override
    public List<Courses> getCourses() {
        DevDatabase devDatabase = new DevDatabase();
        Courses course = new Courses();
        List<Courses> allCourses = new ArrayList<>();
        Connection devConnection = null;
        {
            try {
                String SELECT_COURSE_QUERY = "SELECT course_number, course_name,instructor_number from course";
                devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_COURSE_QUERY);
                while (rs.next()) {
                    String courseNumber = rs.getString("course_number");
                    course.setCourseNumber(courseNumber);
                    String courseName = rs.getString("course_name");
                    course.setCourseName(courseName);
                    String instructorNUmber = rs.getString("instructor_number");
                    course.setInstructor(instructorNUmber);
                    allCourses.add(course);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return allCourses;
    }

}
