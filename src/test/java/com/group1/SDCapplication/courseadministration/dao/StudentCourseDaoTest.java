package com.group1.SDCapplication.courseadministration.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentCourseDaoTest {
	
	StudentCourseDao stCourseDao = new StudentCourseDao("am754815@dal.ca");
	
	@Test
	void testGetCourses() {
		// Test 1: Check if returned list is not null
		assertNotNull(stCourseDao.getCourses(), () -> "error while getting courses from DB");
		
		// Test 2: Check if returned list is not empty
		assertFalse(stCourseDao.getCourses().size() == 0, "Error while getting courses from DB");
		
		// Test 3: Check if returned course(s) number is/are not null
		assertNotNull(stCourseDao.getCourses().get(0).getCourseNumber(), "Error while getting courses from DB");
						
		// Test 4: Check if returned course(s) name is/are not null
		assertNotNull(stCourseDao.getCourses().get(0).getCourseName(), "Error while getting courses from DB");
	}

}
