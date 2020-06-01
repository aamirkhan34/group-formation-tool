package com.group1.SDCapplication.login.services;

import com.group1.SDCapplication.login.jsonwebtoken.JwtTokenUtil;

public class ForgotPasswordValidation {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    public String getToken(String email){
        String token = jwtTokenUtil.generateTokenForgotPassword(email);
        return token;
    }

}
