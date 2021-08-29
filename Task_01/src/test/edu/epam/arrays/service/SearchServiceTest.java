package test.edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.impl.SearchServiceImpl;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class SearchServiceTest {
    @Test
    public void findMaxTest() throws ArrayException {
        CustomArray array = new CustomArray(1, 2, 3, 4, 5);
        SearchServiceImpl service = new SearchServiceImpl();
        int actual = service.findMax(array);
        int expected = 5;
        assertEquals(actual, expected);
    }
    @Test
    public void findMaxStreamTest() throws ArrayException{
        CustomArray array = new CustomArray(9, 2, 5, -9, 0);
        SearchServiceImpl service = new SearchServiceImpl();
        int expected = 9;
        int actual = service.findMaxStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findMinTest() throws ArrayException{
        CustomArray array = new CustomArray(1, 2, 3, 4, -5);
        SearchServiceImpl service = new SearchServiceImpl();
        int actual = service.findMin(array);
        int expected = -5;
        assertEquals(actual, expected);
    }
    @Test
    public void findMinStreamTest() throws ArrayException{
        CustomArray array = new CustomArray(0, 7, 1, -8, 3);
        SearchServiceImpl service = new SearchServiceImpl();
        int expected = -8;
        int actual = service.findMinStream(array);
        assertEquals(actual, expected);
    }
}
