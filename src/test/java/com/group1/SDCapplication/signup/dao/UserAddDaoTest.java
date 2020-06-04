package com.group1.SDCapplication.signup.dao;

import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.models.UserTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAddDaoTest {

    UserAddDao userAddDao = new UserAddDao();

    @Test
    public void userNotExistTest(){
        assertFalse(userAddDao.userNotExist("test5@gmail.com"), ()->"error while searching user");
    }

}
