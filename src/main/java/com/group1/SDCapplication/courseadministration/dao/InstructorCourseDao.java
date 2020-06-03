package com.group1.SDCapplication.courseadministration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.user.dao.Course;

public class InstructorCourseDao implements Course {

	@Override
	public List<Courses> getCourses() {
		DevDatabase devDatabase = new DevDatabase();
		Courses course = new Courses();
		List<Courses> allCourses = new ArrayList<>();
		Connection devConnection;
		{
			try {
				String QUERY = "SELECT C.course_number, C.course_name FROM course C\n"
						+ "JOIN instructor I ON C.instructor_number = I.instructor_number \n"
						+ "JOIN user U ON I.UID = U.UID \n"
						+ "WHERE U.email = 'heywood@dal.ca';";

				devConnection = devDatabase.getConnection();
				Statement stmt = devConnection.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);
				System.out.println(rs.toString());
				while (rs.next()) {
					String courseNumber = rs.getString("course_number");
					course.setCourseNumber(courseNumber);
//                    String courseName = rs.getString("course_name");
					String courseName = rs.getString("course_name");
					course.setCourseName(courseName);
//                    String instructorNUmber = rs.getString("instructor_number");
					String instructorNUmber = "instructor_number";
					course.setInstructor(instructorNUmber);
				}
				allCourses.add(course);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return allCourses;
		
	}
}