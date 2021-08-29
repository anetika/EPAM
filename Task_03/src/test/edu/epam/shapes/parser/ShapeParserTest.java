package test.edu.epam.shapes.parser;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.parser.ShapeParser;
import edu.epam.shapes.reader.ShapeReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShapeParserTest {
    private List<double[]> list;
    private ShapeReader reader;
    private ShapeParser parser;

    @BeforeClass
    public void setUp(){
        reader = new ShapeReader();
        parser = new ShapeParser();
        list = new ArrayList<>();
        list.add(new double[]{0, 0, 2, 0, 0, 2});
        list.add(new double[]{0, 0, 4, 0, 0, 3});
        list.add(new double[]{11, 98, 35, 78, 12, 65});
    }

    @AfterClass
    public void tearDown(){
        reader = null;
        parser = null;
        list = null;
    }

    @Test
    public void parserTestTrue() throws ShapeException{
        List<String> strings = reader.readStrings("src/test/resources/input.txt");
        List<double[]> actual = parser.parseStrings(strings);
        assertEquals(actual, list);
    }
    @Test
    public void parserTestFalse() throws ShapeException{
        List<String> strings = reader.readStrings("src/test/resources/input.txt");
        List<double[]> actual = parser.parseStrings(strings);
        assertNotEquals(actual, new ArrayList<>());
    }

    @Test
    public void parserLengthTestTrue() throws ShapeException{
        List<String> strings = reader.readStrings("src/test/resources/input.txt");
        List<double[]> actual = parser.parseStrings(strings);
        int expected = list.size();
        assertEquals(actual.size(), expected);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void parserNullListTest() throws ShapeException {
        List<double[]> actual = parser.parseStrings(null);
    }

    @Test(expectedExceptions = ShapeException.class)
    public void parserEmptyListTest() throws ShapeException {
        List<double[]> actual = parser.parseStrings(new ArrayList<>());
    }
}
