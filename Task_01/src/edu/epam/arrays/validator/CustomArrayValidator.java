package edu.epam.arrays.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public final class CustomArrayValidator {
    private CustomArrayValidator(){

    }
    private static final String INTEGER_REGEX = "^(0|[-\\\\+]?[1-9][0-9]*)$";

    public static boolean validate(String string){
        var pattern = Pattern.compile(INTEGER_REGEX);
        var matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
