package edu.epam.arrays.parser;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.validator.CustomArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CustomArrayParser {
    static Logger logger = LogManager.getLogger();
    public CustomArray parseToArray(List<String> data, String separator) throws ArrayException {
        if(data == null){
            logger.error("String is null");
            throw new ArrayException("String is null");
        }
        if(separator == null){
            logger.error("Separator is null");
            throw new ArrayException("Separator is null");
        }
        for(var string : data){
            int i = 0;
            String[] strings = string.split(separator);
            int[] array = new int[strings.length];
            boolean isCorrectString = true;
            for (var element : strings){
                if(CustomArrayValidator.validate(element)) {
                    array[i] = Integer.parseInt(element);
                    i++;
                } else{
                  isCorrectString = false;
                  break;
                }
            }
            if(isCorrectString) {
                logger.info("The string is correct " + string);
                return new CustomArray(array);
            }
        }
        logger.error("There is no correct string in data");
        throw new ArrayException("There is no correct string in data");
    }
}
