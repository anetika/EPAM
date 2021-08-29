package test.edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.impl.DefineServiceImpl;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class DefineServiceTest {
    @Test
    public void findSumTest() throws ArrayException {
        CustomArray array = new CustomArray(6, 4, 1, 0, 8);
        DefineServiceImpl service = new DefineServiceImpl();
        int expected = 19;
        int actual = service.findSum(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findSumStreamTest() throws ArrayException {
        CustomArray array = new CustomArray(9, -2, 1, 0, 5);
        DefineServiceImpl service = new DefineServiceImpl();
        int expected = 13;
        int actual = service.findSumStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findAverageTest() throws ArrayException {
        CustomArray array = new CustomArray(6, 7, 2, 1, 10);
        DefineServiceImpl service = new DefineServiceImpl();
        double expected = 5.2;
        double actual = service.findAverage(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findAverageStreamTest() throws ArrayException{
        CustomArray array = new CustomArray(0, 6, 1, -5, 9);
        DefineServiceImpl service = new DefineServiceImpl();
        double expected = 2.2;
        double actual = service.findAverageStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void calculatePositiveElementsTest() throws ArrayException {
        CustomArray array = new CustomArray(3, -4, 9, -1, -7);
        DefineServiceImpl service = new DefineServiceImpl();
        int expected = 2;
        int actual = service.calculatePositiveElements(array);
        assertEquals(actual, expected);
    }
    @Test
    public void calculatePositiveElementsStreamTest() throws ArrayException {
        CustomArray array = new CustomArray(-1, 0, 1, 2);
        DefineServiceImpl service = new DefineServiceImpl();
        long expected = 2;
        long actual = service.calculatePositiveElementsStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void calculateNegativeElementsTest() throws ArrayException{
        CustomArray array = new CustomArray(-9, -1, 0, 6, -2);
        DefineServiceImpl service = new DefineServiceImpl();
        long expected = 3;
        long actual = service.calculateNegativeElements(array);
        assertEquals(actual, expected);
    }
    @Test
    public void calculateNegativeElementsStreamTest() throws ArrayException{
        CustomArray array = new CustomArray(0, 7, -1, 0, -2);
        DefineServiceImpl service = new DefineServiceImpl();
        long expected = 2;
        long actual = service.calculateNegativeElementsStream(array);
        assertEquals(actual, expected);
    }
}
