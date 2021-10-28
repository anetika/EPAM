package edu.epam.webproject.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The class that encrypts password
 */
public class PasswordEncryptor {
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor(){}

    /**
     * Gets instance
     *
     * @return the instance of password encryptor
     */
    public static PasswordEncryptor getInstance(){
        return instance;
    }

    /**
     * Checks password
     *
     * @param password - password
     * @param hashPassword - hash password
     * @return the result of matching
     */
    public boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }

    /**
     * Gets hashed password
     *
     * @param password - password
     * @return hashed password
     */
    public String getHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
