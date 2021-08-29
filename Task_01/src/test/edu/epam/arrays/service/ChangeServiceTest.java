package test.edu.epam.arrays.service;

import edu.epam.arrays.entity.CustomArray;
import edu.epam.arrays.exception.ArrayException;
import edu.epam.arrays.service.impl.ChangeServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ChangeServiceTest {
    @Test
    public void changeElementsTest() throws ArrayException{
        CustomArray array = new CustomArray(9, 0, 5, -1, -5);
        CustomArray expected = new CustomArray(9, 0, 5, 0, 0);
        ChangeServiceImpl service = new ChangeServiceImpl();
        service.changeElements(array);
        assertEquals(array, expected);
    }
    @Test
    public void changeElementsStreamTestTrue() throws ArrayException {
        CustomArray array = new CustomArray(-1, -2, 0, 1, 2);
        CustomArray expected = new CustomArray(0, 0, 0, 1, 2);
        ChangeServiceImpl service = new ChangeServiceImpl();
        service.changeElementsStream(array);
        assertEquals(array, expected);
    }
    @Test
    public void changeElementsStreamTestFalse() throws ArrayException {
        CustomArray array = new CustomArray(-5, 0, 9, -3, 8);
        CustomArray expected = new CustomArray(-5, 0, 0, -3, 0);
        ChangeServiceImpl service = new ChangeServiceImpl();
        service.changeElementsStream(array);
        assertNotEquals(array, expected);
    }
}
