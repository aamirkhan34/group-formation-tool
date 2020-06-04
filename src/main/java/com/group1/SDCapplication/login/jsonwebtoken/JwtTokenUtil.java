package com.group1.SDCapplication.login.jsonwebtoken;


import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import com.group1.SDCapplication.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable , JwtTokenInterface{
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final long JWT_TOKEN_VALIDITY_FORGOT_PASSWORD = 600 * 1000;
    public static final long JWT_TEMP_TOKEN_VALIDITY = 5 * 60;

    private String secret = "CSCI5308";
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        List<String > roles = new ArrayList<>();
        roles.add("Guest");
        return generateTokenWithRoles(user, roles);
    }
    public String generateTokenWithRoles(User user, List<String >roles ){
        Map<String, Object> claims = new HashMap<>();
        claims.put("UID",user.getId());
        claims.put("email",user.getEmail());
        claims.put("first name",user.getFirstname());
        claims.put("last name",user.getLastname());
        claims.put("roles", roles);
        return doGenerateToken(claims, "CSCI5308");
    }
    public String generateTokenForgotPassword(String email) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateTokenForgotPassword(claims, email);
    }


    @Override
    public String generateTemporaryToken(Long UID) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UID",UID);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TEMP_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    private String doGenerateTokenForgotPassword(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_FORGOT_PASSWORD))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean validateUserRole(String token, String role) {
        List<String > roles = (List<String >)getAllClaimsFromToken(token).get("roles");
        return roles.contains(role);
    }
}
