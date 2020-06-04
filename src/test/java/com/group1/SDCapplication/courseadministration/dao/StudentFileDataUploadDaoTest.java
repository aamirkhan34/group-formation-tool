package com.group1.SDCapplication.courseadministration.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.group1.SDCapplication.models.Student;
import com.group1.SDCapplication.models.User;

class StudentFileDataUploadDaoTest {
	
	@Test
	void testCreateNewStudent() {
		StudentFileDataUploadDao sud = new StudentFileDataUploadDao();

		Student s = new Student();
		s.setEmail("sidmalvi@gmail.com");
		s.setBannerId("B11229111");
		s.setLastname("Malvi");
		s.setFirstname("Sid");
		s.setPassword("B112299001001");
		
		// Test 1: Returned status must not be false
		assertTrue(sud.createNewStudent(s), "Student upload failed");
		
		// Test 2: User must exist in the database after above addition
		assertFalse(sud.userNotExist(s.getEmail()), "Student upload failed");
	}

	@Test
	void testCheckUpdateUserRole() {
		StudentFileDataUploadDao sud = new StudentFileDataUploadDao();

		Student s = new Student();
		s.setEmail("sidmalvi@gmail.com");
		s.setBannerId("B11229111");
		s.setLastname("Malvi");
		s.setFirstname("Sid");
		s.setPassword("B112299001001");
		
		// Test 1: Returned status must not be false
		assertTrue(sud.checkUpdateUserRole(s), "Student role update failed");
	}

	@Test
	void testEnrollInCourse() {
		StudentFileDataUploadDao sud = new StudentFileDataUploadDao();

		Student s = new Student();
		s.setEmail("sidmalvi@gmail.com");
		s.setBannerId("B11229111");
		s.setLastname("Malvi");
		s.setFirstname("Sid");
		s.setPassword("B112299001001");
		
		String courseNumber = "CSCI5308";
		
		// Test 1: Returned status must not be false
		assertTrue(sud.enrollInCourse(s, courseNumber), "Student course enrollment failed");
	}

	@Test
	void testAddUserToDB() {
		StudentFileDataUploadDao sud = new StudentFileDataUploadDao();

		Student s = new Student();
		s.setEmail("sidmalvi@gmail.com");
		s.setBannerId("B11229111");
		s.setLastname("Malvi");
		s.setFirstname("Sid");
		s.setPassword("B112299001001");
		
		// Test 1: Returned status must not be false
		assertFalse(sud.addUserToDB(s).isEmpty(), "Student creation/update failed");
	}

}
