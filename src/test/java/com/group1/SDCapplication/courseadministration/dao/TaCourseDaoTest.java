package com.group1.SDCapplication.courseadministration.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.group1.SDCapplication.user.dao.CoursesDao;

class TaCourseDaoTest {
	
	TaCourseDao taCourseDao = new TaCourseDao("test7@gmail.com");

	@Test
	void testGetCourses() {
		// Test 1: Check if returned list is not null
		assertNotNull(taCourseDao.getCourses(), () -> "error while getting courses from DB");
		
		// Test 2: Check if returned list is not empty
		assertFalse(taCourseDao.getCourses().size() == 0, "Error while getting courses from DB");
		
		// Test 3: Check if returned course(s) number is/are not null
		assertNotNull(taCourseDao.getCourses().get(0).getCourseNumber(), "Error while getting courses from DB");
				
		// Test 4: Check if returned course(s) name is/are not null
		assertNotNull(taCourseDao.getCourses().get(0).getCourseName(), "Error while getting courses from DB");
	}

}
