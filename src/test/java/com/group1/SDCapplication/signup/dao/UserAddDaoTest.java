package com.group1.SDCapplication.signup.dao;

import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.models.UserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAddDaoTest {

    UserAdd userAddDao = null;
    @BeforeEach
    public void initialize(){
        userAddDao = new UserAddDao();
    }
    @Test
    public void testAddUserToDB(){
        userAddDao = new UserAddMock();
        User user = new User(1,"firstName","lastName","email","passWord");
        assertTrue(userAddDao.addUserToDB(user).equals("success"),()->"Add user failed");
    }

    @Test
    public void userNotExistTest(){
        assertFalse(userAddDao.userNotExist("test5@gmail.com"), ()->"error while searching user");
    }

}
