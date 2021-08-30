package edu.epam.webproject.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor(){}

    public static PasswordEncryptor getInstance(){
        return instance;
    }

    public boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }

    public String getHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
