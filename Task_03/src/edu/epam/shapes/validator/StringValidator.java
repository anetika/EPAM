package edu.epam.shapes.validator;

import java.util.StringTokenizer;

public class StringValidator {
    public static boolean validateString(String string, String separator){
        StringTokenizer tokenizer = new StringTokenizer(string, separator);
        int counter = tokenizer.countTokens();
        boolean flag = true;
        if (counter != 6){
            flag = false;
        } else {
            flag = PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken())
                    && PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken())
                    && PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken());
        }
        return flag;
    }
}
