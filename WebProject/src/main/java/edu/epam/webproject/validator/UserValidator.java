package edu.epam.webproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.epam.webproject.validator.ValidatorRegex.*;

/**
 * The class that validates user
 */
public class UserValidator {
    public UserValidator(){}

    /**
     * Validates user
     *
     * @param login    - login
     * @param email    - email
     * @param password - password
     * @return the result of matching to the pattern
     */
    public boolean validateUser(String login, String email, String password) {
        return validateLogin(login) && validateEmail(email) && validatePassword(password);
    }

    /**
     * Validates user login
     *
     * @param login - login
     * @return the result of matching to the pattern
     */
    public boolean validateLogin(String login) {
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    /**
     * Validates user email
     *
     * @param email - email
     * @return the result of matching to the pattern
     */
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates user password
     *
     * @param password - password
     * @return the result of matching to the pattern
     */
    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
