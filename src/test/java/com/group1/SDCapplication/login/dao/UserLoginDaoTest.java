package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDaoTest  implements UserLogInTest{


    @Test
    @Override
    public void isUserValidTest() {
        UserLoginDao userLoginDao =new UserLoginDao();
        User user = new User();
        user.setEmail("testemail");
        user.setPassword("testpassword");
        assertTrue((user.getEmail() == "testemail" && user.getPassword()=="testpassword") == true);
        assertTrue(userLoginDao.isUserValid(user.getEmail(), user.getPassword()) == false);

    }


}
