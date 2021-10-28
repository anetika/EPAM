package edu.epam.webproject.validator;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VacancyValidatorTest {
    private final VacancyValidator validator = new VacancyValidator();

    @Test
    public void vacancyValidatorTestTrue(){
        String position = "doctor";
        String companyName = "BelMed";
        BigDecimal salary = BigDecimal.valueOf(1000);
        String description = "Good conditions for working";
        assertTrue(validator.validateVacancy(position, companyName, salary, description));
    }

    @Test
    public void vacancyValidatorTestFalse(){
        String position = "123456";
        String companyName = "BelMed";
        BigDecimal salary = BigDecimal.valueOf(1000);
        String description = "Good conditions for working";
        assertFalse(validator.validateVacancy(position, companyName, salary, description));
    }

}
