package test.edu.epam.arrays.reader;

import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.reader.CustomArrayReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ReaderTest {
    @Test
    public void readerTest() throws ArrayException{
        CustomArrayReader reader = new CustomArrayReader();
        List<String> actual = reader.readArray("src/test/resources/input.txt");
        List<String> expected = new ArrayList<>();
        expected.add("4 7 5 9 0");
        assertEquals(actual, expected);
    }
}
