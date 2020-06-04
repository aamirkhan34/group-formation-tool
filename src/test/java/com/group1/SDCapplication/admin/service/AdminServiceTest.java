package com.group1.SDCapplication.admin.service;

import com.group1.SDCapplication.admin.dao.CoursesDaoMock;
import com.group1.SDCapplication.admin.dao.IAdminRepository;
import com.group1.SDCapplication.admin.services.AdminService;
import com.group1.SDCapplication.user.dao.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminServiceTest {
    IAdminRepository coursesDao;

    @BeforeEach
    public void Initialize(){
        coursesDao = new CoursesDaoMock();
    }

    @Test
    public void loadAllCourseTest(){
        assertNotNull(coursesDao.loadAllCourses());
    }

    @Test
    public void findByIdTest(){
        assertNotNull(coursesDao.findById("2"));
    }

    @Test
    public void deleteCourseTest(){
        assertNotNull(coursesDao.deleteCourseByCourseNumber(Long.valueOf(2)));
    }
}
