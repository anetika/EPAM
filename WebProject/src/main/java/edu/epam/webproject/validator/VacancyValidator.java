package edu.epam.webproject.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.epam.webproject.validator.ValidatorRegex.*;

/**
 * The class that validates vacancy
 */
public class VacancyValidator {
    public VacancyValidator(){}
    /**
     * Validates vacancy
     *
     * @param position - position
     * @param companyName - company name
     * @param salary - salary
     * @param description - description
     * @return the result of matching to the pattern
     */
    public boolean validateVacancy(String position, String companyName, BigDecimal salary, String description){
        return validatePosition(position) && validateCompanyName(companyName) && validateSalary(salary) && validateDescription(description);
    }

    /**
     * Validates vacancy position
     *
     * @param position - position
     * @return the result of matching to the pattern
     */
    public boolean validatePosition(String position) {
        Pattern pattern = Pattern.compile(POSITION_REGEX);
        Matcher matcher = pattern.matcher(position);
        return matcher.matches();
    }

    /**
     * Validates company name
     *
     * @param companyName - company name
     * @return the result of matching to the pattern
     */
    public boolean validateCompanyName(String companyName) {
        Pattern pattern = Pattern.compile(COMPANY_NAME_REGEX);
        Matcher matcher = pattern.matcher(companyName);
        return matcher.matches();
    }

    /**
     * Validates salary
     *
     * @param salary - salary
     * @return the result of matching to the pattern
     */
    public boolean validateSalary(BigDecimal salary) {
        Pattern pattern = Pattern.compile(SALARY_REGEX);
        Matcher matcher = pattern.matcher(salary.toString());
        return matcher.matches();
    }

    /**
     * Validates vacancy description
     *
     * @param description - description
     * @return the result of matching to the pattern
     */
    public boolean validateDescription(String description) {
        Pattern pattern = Pattern.compile(DESCRIPTION_REGEX);
        Matcher matcher = pattern.matcher(description);
        return matcher.matches();
    }
}
