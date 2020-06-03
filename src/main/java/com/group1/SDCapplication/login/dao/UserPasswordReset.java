package com.group1.SDCapplication.login.dao;

public interface UserPasswordReset {
    public boolean passwordReset(String userEmail, String userPassword);
    public boolean updatePassword(String email, String encryptedPassword);
    public long getUserID(String email);
}
