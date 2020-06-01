package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

public class UserPasswordReset {

    public boolean passwordReset(String userEmail, String userPassword){
        PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();
        String encryptedPassword = passwordEncryptDecrypt.passwordEncrypt(userPassword);
        return true;
    }
}
