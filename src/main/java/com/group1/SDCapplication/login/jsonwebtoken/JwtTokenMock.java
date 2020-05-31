package com.group1.SDCapplication.login.jsonwebtoken;

import com.group1.SDCapplication.models.User;
import io.jsonwebtoken.Claims;

import java.util.List;

public class JwtTokenMock implements JwtTokenInterface {
    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return null;
    }

    @Override
    public String generateTokenWithRoles(User user, List<String> roles) {
        return null;
    }

    @Override
    public boolean validateUserRole(String token, String role) {
        return false;
    }
}
