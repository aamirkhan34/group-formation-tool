package com.group1.SDCapplication.courseadministration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group1.SDCapplication.courseadministration.services.EmailNotification;
import com.group1.SDCapplication.courseadministration.services.UserNotification;
import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.datasource.ProductionDatabase;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.login.services.UserValidation;
import com.group1.SDCapplication.models.Student;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.dao.UserAdd;
import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

public class StudentFileDataUploadDao implements UserAdd {

	public boolean createNewStudent(Student user) {
		ProductionDatabase dev = new ProductionDatabase();

		String firstName = user.getFirstname();
		String lastName = user.getLastname();
		String email = user.getEmail();
		String password = user.getPassword();
		String bannerId = user.getBannerId();
		String encryptedPassword = PasswordEncryptDecrypt.passwordEncrypt(password);

		try {
			if (userNotExist(user.getEmail())) {
				int res;
				Connection devConnection = dev.getConnection();
				Statement stmt = devConnection.createStatement();

				String USER_INSERT_QUERY = "INSERT INTO user (first_name, last_name, email, password) VALUES (" + "'"
						+ firstName + "'" + "," + "'" + lastName + "'" + "," + "'" + email + "'" + "," + "'"
						+ encryptedPassword + "'" + ")";
				res = stmt.executeUpdate(USER_INSERT_QUERY);

				String ID_ROLE_QUERY = "Select UID from user where email = " + "'" + email + "'";
				ResultSet rs = stmt.executeQuery(ID_ROLE_QUERY);
				int userID = 0;
				while (rs.next()) {
					userID = rs.getInt("UID");
				}

				String ROLE_INSERT_QUERY = "INSERT INTO user_role (UID, role_id) VALUES (" + "'" + userID + "'" + ","
						+ "4)";
				stmt.executeUpdate(ROLE_INSERT_QUERY);

				String STUDENT_INSERT_QUERY = "INSERT INTO student (banner_number, UID, more_info) " + "VALUES ('"
						+ bannerId + "','" + userID + "','')";
				stmt.executeUpdate(STUDENT_INSERT_QUERY);

				devConnection.close();

				// Notifying users
				String message = "Your account has been created as a Student. You can login with password: "
						+ user.getPassword();
				UserNotification en = new EmailNotification(user.getEmail(), message);
				en.notifyUser();

				return true;

			} else {
				return true;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return false;
	}

	public boolean checkUpdateUserRole(Student user) {
		ProductionDatabase dev = new ProductionDatabase();

		boolean isRoleStudent = false;
		String email = user.getEmail();
		String password = user.getPassword();
		String bannerId = user.getBannerId();

		try {
			int res;
			Connection devConnection = dev.getConnection();
			Statement stmt = devConnection.createStatement();

			List<String> userRoles = new ArrayList<>();
			UserValidation userValidation = new UserValidation();
			UserCredentials userCredentials = new UserCredentials();
			userCredentials.setEmail(email);
			userCredentials.setPassword(password);
			userRoles = userValidation.getUserRoles(userCredentials);

			for (String role : userRoles) {
				if (role.equals("student")) {
					isRoleStudent = true;
					break;
				}
			}

			if (!isRoleStudent) {
				String UID_QUERY = "Select UID from user where email = " + "'" + email + "'";
				ResultSet rs = stmt.executeQuery(UID_QUERY);
				int userID = 0;
				while (rs.next()) {
					userID = rs.getInt("UID");
				}

				String ROLE_INSERT_QUERY = "INSERT INTO user_role (UID, role_id) VALUES (" + "'" + userID + "'" + ","
						+ "4)";
				stmt.executeUpdate(ROLE_INSERT_QUERY);

				String STUDENT_INSERT_QUERY = "INSERT INTO student (banner_number, UID, more_info) " + "VALUES ('"
						+ bannerId + "','" + userID + "','')";
				stmt.executeUpdate(STUDENT_INSERT_QUERY);

				// Notifying users
				String message = "Your account type has been changed to Student.";
				UserNotification en = new EmailNotification(user.getEmail(), message);
				en.notifyUser();
			}
			devConnection.close();

			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return false;
	}

	public boolean enrollInCourse(Student user, String courseNumber) {
		ProductionDatabase dev = new ProductionDatabase();

		String bannerId = user.getBannerId();

		try {
			int res;
			Connection devConnection = dev.getConnection();
			Statement stmt = devConnection.createStatement();

			String EN_CHK_QUERY = "Select banner_number, course_number from student_learning_course where banner_number = '"
					+ bannerId + "' and course_number = '" + courseNumber + "';";
			ResultSet rs = stmt.executeQuery(EN_CHK_QUERY);
			
			String fetchData = "";
			while (rs.next()) {
				fetchData = rs.getString("banner_number");
			}
			
			if (fetchData.isEmpty()) {
				String ENROLL_QUERY = "INSERT INTO student_learning_course (banner_number, course_number) VALUES ('"
						+ bannerId + "','" + courseNumber + "');";
				stmt.executeUpdate(ENROLL_QUERY);
				
				// Notifying users
				String message = "Your enrollment in course: " + courseNumber + " is complete.";
				UserNotification en = new EmailNotification(user.getEmail(), message);
				en.notifyUser();
			}
			devConnection.close();
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return false;
	}

	@Override
	public String addUserToDB(User user) {
		boolean status1 = false;

		if (userNotExist(user.getEmail())) {
			status1 = createNewStudent((Student) user);
		} else {
			status1 = checkUpdateUserRole((Student) user);
		}

		if (!status1) {
			return "";
		}

		return "success";
	}

	@Override
	public boolean userNotExist(String email) {
		ProductionDatabase dev = new ProductionDatabase();
		String USER_EXIST = "SELECT email FROM user where email =" + "'" + email + "'";
		boolean result = false;
		ResultSet rs = null;
		try {
			Connection devConnection = dev.getConnection();
			Statement stmt = devConnection.createStatement();
			rs = stmt.executeQuery(USER_EXIST);
			if (rs.next()) {
				result = false;
				devConnection.close();
			} else {
				result = true;
				devConnection.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return result;
	}

}
