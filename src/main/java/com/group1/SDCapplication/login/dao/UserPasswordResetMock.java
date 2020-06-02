package com.group1.SDCapplication.login.dao;

import com.group1.SDCapplication.signup.security.PasswordEncryptDecrypt;

public class UserPasswordResetMock implements UserPasswordReset{

    PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();

    @Override
    public boolean passwordReset(String userEmail, String userPassword) {

        userEmail = "testuser";
        userPassword = "testpassword";

        String encryptedPassword = passwordEncryptDecrypt.passwordEncrypt(userPassword);
        if(updatePassword(userEmail, encryptedPassword)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean updatePassword(String email, String encryptedPassword) {
        String decryptedpassword = passwordEncryptDecrypt.passwordDecrypt(encryptedPassword);
        if (email == "testuser" && decryptedpassword == "testpassword"){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public long getUserID(String email) {
        long userId = 1;
        if (email == "testuser") {
            return userId;
        } else {
            return 0;
        }
    }
}
