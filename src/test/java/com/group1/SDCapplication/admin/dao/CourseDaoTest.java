package com.group1.SDCapplication.admin.dao;

import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.dao.UserAdd;
import com.group1.SDCapplication.signup.dao.UserAddDao;
import com.group1.SDCapplication.signup.dao.UserAddMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseDaoTest {
    Courses course = null;
    @BeforeEach
    public void initialize(){
        course = new Courses();
    }

}
