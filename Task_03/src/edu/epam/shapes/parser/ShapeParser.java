package edu.epam.shapes.parser;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShapeParser {
    private static final Logger logger = LogManager.getLogger();
    public List<double[]> parseStrings(List<String> list) throws ShapeException {
        if (list == null){
            logger.error("List is null");
            throw new ShapeException("List is null");
        }
        if (list.isEmpty()){
            logger.error("List is empty");
            throw new ShapeException("List is empty");
        }
        List<double[]> result = new ArrayList<>();
        for (var item: list){
            if (StringValidator.validateString(item, " ")){
                result.add(parseString(item));
            }
        }
        return result;
    }
    private double[] parseString(String string){
        StringTokenizer tokenizer = new StringTokenizer(string);
        double[] result = new double[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()){
            result[i] = Double.parseDouble(tokenizer.nextToken());
            i++;
        }
        logger.info("String was parsed");
        return result;
    }
}
