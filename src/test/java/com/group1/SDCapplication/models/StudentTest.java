package com.group1.SDCapplication.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	void testSetBannerId() {
		Student s = new Student();
        s.setBannerId("B11223345");;
        assertTrue(s.getBannerId().equals("B11223345"));
	}

}
