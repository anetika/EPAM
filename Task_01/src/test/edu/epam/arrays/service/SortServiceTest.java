package test.edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.SortService;
import edu.epam.arrays.service.impl.SortServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SortServiceTest {
    @Test
    public void bubbleSortTest() throws ArrayException{
        CustomArray array = new CustomArray(0, 8, 7, 1, 5);
        SortServiceImpl service = new SortServiceImpl();
        CustomArray expected = new CustomArray(0, 1, 5, 7, 8);
        service.bubbleSort(array);
        assertEquals(array, expected);
    }
    @Test
    public void insertSortTest() throws ArrayException{
        CustomArray array = new CustomArray(9, 0, 2, 7, 1);
        SortServiceImpl service = new SortServiceImpl();
        CustomArray expected = new CustomArray(0, 1, 2, 7, 9);
        service.insertSort(array);
        assertEquals(array, expected);
    }
    @Test
    public void selectionSortTest() throws ArrayException{
        CustomArray array = new CustomArray(2, 9, 5, -3, 0);
        SortService service = new SortServiceImpl();
        CustomArray expected = new CustomArray(-3, 0, 2, 5, 9);
        service.selectionSort(array);
        assertEquals(array, expected);
    }
    @Test
    public void sortStreamTestTrue() throws ArrayException {
        CustomArray array = new CustomArray(5, 4, 3, 2, 1);
        SortServiceImpl service = new SortServiceImpl();
        CustomArray expected = new CustomArray(1, 2, 3, 4, 5);
        service.sortStream(array);
        assertEquals(array, expected);
    }
}
