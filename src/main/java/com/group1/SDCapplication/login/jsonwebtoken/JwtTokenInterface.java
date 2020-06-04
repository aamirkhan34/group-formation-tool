package com.group1.SDCapplication.login.jsonwebtoken;

import com.group1.SDCapplication.models.User;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public interface JwtTokenInterface {
    public String getUsernameFromToken(String token);
    public Claims getAllClaimsFromToken(String token);
    public String generateTokenWithRoles(User user, List<String > roles );
    public String generateTemporaryToken(Long UID);
    public boolean validateUserRole(String token, String role);
}
