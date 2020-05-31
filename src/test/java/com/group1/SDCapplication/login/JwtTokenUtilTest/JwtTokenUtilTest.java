package com.group1.SDCapplication.login.JwtTokenUtilTest;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;
import com.group1.SDCapplication.login.models.UserCredentials;
import com.group1.SDCapplication.models.User;
import org.junit.jupiter.api.BeforeAll;
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
    private JwtTokenUtil jwtTokenUtil;
    private User testUser;
    private String  token;
    private List<String > roles;

    @BeforeAll
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
    public void testGenerateToken(){
        assertFalse(null == token, () -> "Token not generated correctly");

    }
    @Test
    public void testGetUsernameFromToken(String token) {
        assertTrue(jwtTokenUtil.getAllClaimsFromToken(token).getSubject().equals("CSCI5308"),()-> "Subject extracted correctly");
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserCredentials userCredentials) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userCredentials.getEmail());
    }
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public void testValidateToken(String token, UserDetails userDetails) {
        assertTrue((jwtTokenUtil.getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token)));
    }
}