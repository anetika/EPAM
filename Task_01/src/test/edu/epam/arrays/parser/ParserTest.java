package test.edu.epam.arrays.parser;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.parser.CustomArrayParser;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ParserTest {
    @Test
    public void parserTest() throws ArrayException{
        CustomArrayParser parser = new CustomArrayParser();
        List<String> strings = new ArrayList<>();
        strings.add("2c6 9j");
        strings.add("4 7 5 9 0");
        CustomArray array = parser.parseToArray(strings, " ");
        CustomArray expected = new CustomArray(4, 7, 5, 9, 0);
        assertEquals(array, expected);
    }
    @Test(expectedExceptions = ArrayException.class)
    public void parserTestException() throws ArrayException {
        CustomArrayParser parser = new CustomArrayParser();
        List<String> strings = new ArrayList<>();
        strings.add("6y9nh0");
        parser.parseToArray(strings, " ");
    }

}
