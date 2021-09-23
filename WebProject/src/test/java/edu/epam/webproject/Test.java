package edu.epam.webproject;

import edu.epam.webproject.util.PasswordEncryptor;

public class Test {
    @org.testng.annotations.Test
    public void test(){
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        String hash = encryptor.getHashedPassword("123456");
        System.out.println(hash);
    }
}
