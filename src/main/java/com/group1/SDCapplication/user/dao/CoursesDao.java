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

        List<Courses> allCourses = new ArrayList<>();
            try {
                String SELECT_COURSE_QUERY = "SELECT co.course_number AS course_number, co.course_name as course_name, " +
                        "us.first_name as firstname from course as co " +
                        "inner join instructor as inst inner join user as us on " +
                        "co.instructor_number = inst.instructor_number and inst.UID = us.UID";
                Connection devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_COURSE_QUERY);
                while(rs.next()) {
                    Courses course = new Courses();
                    String courseNumber = rs.getString("course_number");
                    course.setCourseNumber(courseNumber);
                    String courseName = rs.getString("course_name");
                    course.setCourseName(courseName);
                    String instructorName = rs.getString("firstname");
                    course.setInstructor_name(instructorName);
                    allCourses.add(course);
                }
                devConnection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return allCourses;
    }
}
