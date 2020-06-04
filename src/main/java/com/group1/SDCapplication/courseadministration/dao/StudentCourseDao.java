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

public class StudentCourseDao implements Course {

	private String email;

	public StudentCourseDao(String email) {
		super();
		this.email = email;
	}

	@Override
	public List<Courses> getCourses() {
		DevDatabase devDatabase = new DevDatabase();
		Courses course = new Courses();
		List<Courses> allCourses = new ArrayList<>();

		try {
			String QUERY = "SELECT C.course_number, C.course_name FROM course C\n"
					+ "JOIN student_learning_course SLC ON C.course_number = SLC.course_number \n"
					+ "JOIN student S ON SLC.banner_number = S.banner_number \n" + "JOIN user U ON S.UID = U.UID \n"
					+ "WHERE U.email = '" + email + "';";

			Connection devConnection = devDatabase.getConnection();
			Statement stmt = devConnection.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);

			while (rs.next()) {
				String courseNumber = rs.getString("course_number");
				course.setCourseNumber(courseNumber);

				String courseName = rs.getString("course_name");
				course.setCourseName(courseName);
			}
			allCourses.add(course);
			devConnection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return allCourses;
	}

}
