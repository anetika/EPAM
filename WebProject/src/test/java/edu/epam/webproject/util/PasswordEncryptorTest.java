package edu.epam.webproject.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PasswordEncryptorTest {
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();

    @Test
    public void passwordEncryptorTestTrue(){
        String password = "123456";
        String hashedPassword = passwordEncryptor.getHashedPassword(password);
        boolean actual = passwordEncryptor.checkPassword(password, hashedPassword);
        assertTrue(actual);
    }
}
