package edu.epam.shapes.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointValidator {
    private static final String DOUBLE_REGEXP = "^(0|[-\\+]?[1-9][0-9]*)$";
    public static boolean validatePoint(String x, String y){
        Pattern pattern = Pattern.compile(DOUBLE_REGEXP);
        Matcher matcher1 = pattern.matcher(x);
        Matcher matcher2 = pattern.matcher(y);
        boolean flag = false;
        if (matcher1.matches() && matcher2.matches()){
            flag = true;
        }
        return flag;
    }
}
