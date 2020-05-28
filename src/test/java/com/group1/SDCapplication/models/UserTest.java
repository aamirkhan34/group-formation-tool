package com.group1.SDCapplication.models;
import static org.junit.jupiter.api.Assertions.*;
import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.Test;


public class UserTest{

    @Test
    public void setFirstNameTest(){
        User u = new User();
        u.setFirstname("prem");
        assertTrue(u.getFirstname().equals("prem"));
    }

    @Test
    public void setLastnameTest(){
        User u = new User();
        u.setLastname("menni");
        assertTrue(u.getLastname().equals("menni"));
    }

    @Test
    public void setEmailTest(){
        User u = new User();
        u.setEmail("pr775390@dal.ca");
        assertTrue(u.getEmail().equals("pr775390@dal.ca"));
    }

    @Test
    public void setPassword(){
        User u = new User();
        u.setPassword("qwd,89.yy");
        assertTrue(u.getPassword().equals("qwd,89.yy"));
    }
}
