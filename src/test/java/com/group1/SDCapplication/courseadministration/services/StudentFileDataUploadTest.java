package com.group1.SDCapplication.courseadministration.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class StudentFileDataUploadTest {
	MockMultipartFile testFile = new MockMultipartFile("file", "test_students_data.csv", "text/plain",
			"banner_id,last_name,first_name,email\nB11231141,kh,al,aamirkhan26494@gmail.com".getBytes());
	
	@Test
	void testGetParsedCsvData() {
		StudentFileDataUpload sfd = new StudentFileDataUpload(testFile);
		
		// Test 1: Check if returned list is not null
		assertNotNull(sfd.getParsedCsvData(), () -> "CSV parsing failed");
		
		// Test 2: Check if returned list is not empty
		assertFalse(sfd.getParsedCsvData().size() == 0, "CSV parsing failed");
		
		// Test 3: Check if returned course(s) number is/are not null
		assertFalse(sfd.getParsedCsvData().get(0).length == 0, "CSV parsing failed");
	}
	
	@Test
	void testUploadData() {
		UserFileDataUpload sfd = new StudentFileDataUpload(testFile);
		
		// Test 1: Check if upload was successful. It can only fail due to some exception.
		assertTrue(sfd.uploadData(), "CSV upload failed");
	}

}
