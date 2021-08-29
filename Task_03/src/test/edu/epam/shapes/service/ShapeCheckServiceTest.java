package test.edu.epam.shapes.service;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.service.CheckService;
import edu.epam.shapes.service.impl.CheckServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapeCheckServiceTest {
    private CheckService service;
    private ShapeFactory factory;
    @BeforeClass
    public void setUp(){
        service = new CheckServiceImpl();
        factory = new ShapeFactory();
    }
    @AfterClass
    public void tearDown(){
        service = null;
    }
    @Test
    public void isTriangleCheckTestTrue() {
        double[] array = {0, 0, 4, 0, 0, 3};
        boolean actual = service.isTriangle(array);
        boolean expected = true ;
        assertEquals(actual, expected);
    }
    @Test
    public void isRightTriangleCheckTestTrue() throws ShapeException{
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        boolean actual = service.isRightTriangle(triangle);
        boolean expected = true;
        assertEquals(actual, expected);
    }
    @Test
    public void isIsoscelesTriangleCheckTestTrue() throws ShapeException{
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 2, 0, 0, 2});
        boolean actual = service.isIsoscelesTriangle(triangle);
        assertTrue(actual);
    }
    @Test
    public void isEquilateralTriangleCheckTestFalse() throws ShapeException{
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 2, 0, 0, 2});
        boolean actual = service.isEquilateralTriangle(triangle);
        assertFalse(actual);
    }
    @Test
    public void isObtuseTriangleCheckTestTrue() throws ShapeException{
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 10, 0, 5, 1});
        boolean actual = service.isObtuseTriangle(triangle);
        boolean expected = true;
        assertEquals(actual, expected);
    }
    @Test
    public void isAcuteAngledTriangleCheckTestFalse() throws ShapeException{
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 10, 0, 5, 1});
        boolean actual = service.isAcuteAngledTriangle(triangle);
        assertFalse(actual);
    }
}
