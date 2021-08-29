package test.edu.epam.parsing.validator;

import edu.epam.parsing.exception.DepositException;
import edu.epam.parsing.validator.XmlValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValidatorTest {
    @Test
    public void validatorTestTrue() throws DepositException {
        boolean actual = XmlValidator.validateXML("resources/deposits.xml");
        assertTrue(actual);
    }

    @Test
    public void validatorTestFalse() throws DepositException {
        boolean actual = XmlValidator.validateXML("");
        assertFalse(actual);
    }
}
