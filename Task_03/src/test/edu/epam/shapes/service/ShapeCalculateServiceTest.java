package test.edu.epam.shapes.service;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ShapeCalculateServiceTest {
    private CalculateService service;
    private ShapeFactory factory;
    @BeforeClass
    public void setUp(){
        service = new CalculateServiceImpl();
        factory = new ShapeFactory();
    }
    @AfterClass
    public void tearDown(){
        service = null;
    }
    @Test
    public void calculateSquareTestTrue() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        double actual = service.square(triangle);
        double expected = 6 ;
        assertEquals(actual, expected);
    }
    @Test
    public void calculateSquareTestFalse() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        double actual = service.square(triangle);
        double expected = 3;
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ShapeException.class)
    public void calculateSquareTestException() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{});
        double actual = service.square(triangle);
        double expected = 3;
        assertNotEquals(actual, expected);
    }
    @Test
    public void calculatePerimeterTestTrue() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        double actual = service.perimeter(triangle);
        double expected = 12;
        assertEquals(actual, expected);
    }
    @Test
    public void calculatePerimeterTestFalse() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        double actual = service.perimeter(triangle);
        double expected = 10;
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ShapeException.class)
    public void calculatePerimeterTestException() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 1, 1, 2, 2});
        double actual = service.perimeter(triangle);
        double expected = 10;
        assertNotEquals(actual, expected);
    }
}
