package edu.epam.webproject.validator;

public class ValidatorRegex {
    public static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{3,16}$";
    public static final String EMAIL_REGEX = "[a-z].?([-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(com|net|org|pro|tel|travel|ru)";
    public static final String PASSWORD_REGEX = "[A-Za-z0-9_-]{6,16}";

    public static final String POSITION_REGEX = "^[А-ЯA-Zа-яa-z-]+$";
    public static final String COMPANY_NAME_REGEX = "^[A-ZА-Яa-zа-я-_]+$";
    public static final String SALARY_REGEX = "^[1-9][0-9]+$";
    public static final String DESCRIPTION_REGEX = "^[\\w\\s,.!?:;\"'-_]+$";

    private ValidatorRegex(){}
}
