package edu.epam.webproject.validator;

import edu.epam.webproject.entity.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {

    private final UserValidator validator = new UserValidator();
    @Test
    public void userValidatorTestTrue(){
        String login = "Ann";
        String email = "anna.ksenevich@mail.ru";
        String password = "123456";
        assertTrue(validator.validateUser(login, email, password));
    }

    @Test
    public void userValidatorTestFalse(){
        String login = "12";
        String email = "ANNA";
        String password = "122378";
        assertFalse(validator.validateUser(login, email, password));
    }
}
