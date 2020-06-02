package com.group1.SDCapplication.user.dao;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoursesDaoTest {

    CoursesDao coursesDao = new CoursesDao();

    @Test
    public void getCourses(){
        assertNotNull(coursesDao.getCourses(), () -> "error while getting courses from DB");

    }
}
