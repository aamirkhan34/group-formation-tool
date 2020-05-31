package com.group1.SDCapplication.login.JwtTokenUtilTest;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenInterface;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenMock;
import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestComponent
public class JwtTokenUtilTest implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "CSCI5308";
    private JwtTokenInterface jwtTokenUtil;
    private User testUser;
    private String  token;
    private List<String > roles;

    @BeforeEach
    public void generateTestUser(){
        testUser = new User(5308L,"test","family","test@dal.ca","password");
        roles = new ArrayList<String >();
        roles.add("Student");
        roles.add("TA");
        jwtTokenUtil = new JwtTokenUtil();

        token = jwtTokenUtil.generateTokenWithRoles(testUser,roles);
    }
//
    @Test
    @Order(1)
    public void testGenerateToken(){
        assertFalse(null == token, () -> "Token not generated correctly");
    }

    @Test
    @Order(1)
    public void testGenerateTemporaryToken(){
        String  tempToken = jwtTokenUtil.generateTemporaryToken(10010L);
        assertFalse(null == tempToken, () -> "Token not generated correctly");

    }

    @Test
    @Order(2)
    public void testGetUsernameFromToken() {
        assertTrue(jwtTokenUtil.getAllClaimsFromToken(token).getSubject().equals("CSCI5308"),()-> "Subject not extracted correctly");
    }
    @Test
    @Order(3)
    public void testGetAllClaimsFromToken() {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
        System.out.println(claims.get("UID"));
        assertTrue((Long.valueOf((Integer)claims.get("UID"))).equals(5308L),()-> "UID not extracted correctly");
        assertTrue(claims.get("first name").equals("test"),()-> "first name not extracted correctly");
        assertTrue(claims.get("last name").equals("family"),()-> "last name not extracted correctly");
        assertTrue(claims.get("email").equals("test@dal.ca"),()-> "email not extracted correctly");
        assertTrue(claims.get("roles").equals(roles),()-> "roles not extracted correctly");
    }
    @Test
    @Order(3)
    public void testValidateUserRole(){
//        jwtTokenUtil = new JwtTokenMock();
        assertTrue(jwtTokenUtil.validateUserRole(token,"Student"), ()-> "Role validated failed");
        assertTrue(jwtTokenUtil.validateUserRole(token,"TA"), ()-> "Role validated failed");
        assertFalse(jwtTokenUtil.validateUserRole(token,"Guest"), ()-> "Role validated failed");
        assertFalse(jwtTokenUtil.validateUserRole(token,"Instructor"), ()-> "Role validated failed");
        assertFalse(jwtTokenUtil.validateUserRole(token,"Admin"), ()-> "Role validated failed");
    }

}