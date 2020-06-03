package com.group1.SDCapplication.signup.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PasswordEncryptDecryptTest {
    PasswordEncryptDecrypt passwordEncryptDecrypt = new PasswordEncryptDecrypt();

    @Test
    public void passwordEncrypt(){
        String encryptedPassword = passwordEncryptDecrypt.passwordEncrypt("testpassword");
        String decryptedPassword = passwordEncryptDecrypt.passwordDecrypt(encryptedPassword);
        assertEquals(decryptedPassword, "testpassword", () -> "Error in encryption and decryption of password");
    }
}
