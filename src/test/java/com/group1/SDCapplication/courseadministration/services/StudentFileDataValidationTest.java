package com.group1.SDCapplication.courseadministration.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class StudentFileDataValidationTest {
	MockMultipartFile testFile = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
			"banner_id,last_name,first_name,email".getBytes());
	
	@Test
	void testValidateFileType() {		
		UserFileDataValidation sfd1 = new StudentFileDataValidation(testFile);
		
		// Test 1: Check if file type is CSV
		assertTrue(sfd1.validateFileType(), "Filetype validation failed");
		
		// Test 2: Check if file type is not CSV
		MockMultipartFile testNotCsvFile = new MockMultipartFile("file", "test_students_data.xlsx", "text/plain",
				"banner_id,last_name,first_name,email".getBytes());
		
		UserFileDataValidation sfd2 = new StudentFileDataValidation(testNotCsvFile);
		
		assertFalse(sfd2.validateFileType(), "Filetype validation failed");
	}

	@Test
	void testValidateFields() {
		UserFileDataValidation sfd1 = new StudentFileDataValidation(testFile);
		
		// Test 1: Check if file type is CSV
		assertTrue(sfd1.validateFields(), "CSV file column validation failed");
		
		// Test 2: Check if file type is not CSV
		MockMultipartFile testFile2 = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
				"banner_ID,three,first_name,one".getBytes());
		
		UserFileDataValidation sfd2 = new StudentFileDataValidation(testFile2);
		
		assertFalse(sfd2.validateFields(), "CSV file column validation failed");
	}
	
	@Test
	void testValidateEmail() {
		StudentFileDataValidation sfd1 = new StudentFileDataValidation(testFile);
		
		// Test 1: Check if email is not empty
		assertFalse(sfd1.validateEmail(""), "Email validation failed");
		
		// Test 2: Check email structure: invalid case
		assertFalse(sfd1.validateEmail("aka123"), "Email validation failed");
		
		// Test 3: Check email structure: valid case
		assertTrue(sfd1.validateEmail("aka123@yahoo.co.in"), "Email validation failed");
	}
	
	@Test
	void testValidateBannerId() {
		StudentFileDataValidation sfd1 = new StudentFileDataValidation(testFile);
		
		// Test 1: Check if banner_id is not empty
		assertFalse(sfd1.validateBannerId(""), "banner_id validation failed");
		
		// Test 2: Check banner_id structure: invalid case		
		assertFalse(sfd1.validateBannerId("C1223421"), "banner_id validation failed");
		
		// Test 3: Check banner_id structure: invalid case - non-alphanumeric
		assertFalse(sfd1.validateBannerId("C122-3421"), "banner_id validation failed");
		
		// Test 4: Check banner_id structure: valid case		
		assertTrue(sfd1.validateBannerId("B00236161"), "banner_id validation failed");
	}

	@Test
	void testValidateData() {
		// Test 1: Validation with incorrect BannerID
		MockMultipartFile testFile1 = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
				"banner_id,last_name,first_name,email\n'',kh,al,ak123@yahoo.com".getBytes());
		
		UserFileDataValidation sfd1 = new StudentFileDataValidation(testFile1);
		
		assertFalse(sfd1.validateData(), "CSV data validation failed");
		
		// Test 2: Validation with incorrect Email
		MockMultipartFile testFile2 = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
				"banner_id,last_name,first_name,email\nB11231123,kh,al,ak123.com".getBytes());
		
		UserFileDataValidation sfd2 = new StudentFileDataValidation(testFile2);
		
		assertFalse(sfd2.validateData(), "CSV data validation failed");
		
		// Test 3: Validation with correct case
		MockMultipartFile testFile3 = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
				"banner_id,last_name,first_name,email\nB11231123,kh,al,ak123@gmail.com".getBytes());
		
		UserFileDataValidation sfd3 = new StudentFileDataValidation(testFile3);
		
		assertTrue(sfd3.validateData(), "CSV data validation failed");
		
		// Test 4: Validation with correct case 2
		MockMultipartFile testFile4 = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
				"banner_id,last_name,first_name,email\nB11231123,'','',ak123@gmail.com".getBytes());
		
		UserFileDataValidation sfd4 = new StudentFileDataValidation(testFile4);
		
		assertTrue(sfd4.validateData(), "CSV data validation failed");
	}

}
