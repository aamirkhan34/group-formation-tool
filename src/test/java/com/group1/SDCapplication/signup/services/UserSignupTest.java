package com.group1.SDCapplication.signup.services;


import com.group1.SDCapplication.models.User;
import com.group1.SDCapplication.signup.dao.UserAdd;
import com.group1.SDCapplication.signup.dao.UserAddMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserSignupTest {

    UserAdd userAddDao;

    @BeforeEach
    public void initialise(){
        userAddDao =  new UserAddMock();
    }

    @Test
    public void addUsertoDB()
    {
        User user = new User();
        user.setEmail("email");
        user.setPassword("passWord");
        user.setFirstname("firstName");
        user.setLastname("lastName");
        assertEquals((userAddDao.addUserToDB(user)), "success", () -> "Issue in adding user to database");
    }

    @Test
    public void userNotExist(){
        boolean result = userAddDao.userNotExist("testemail");
        assertTrue(result, "true");
    }
}
