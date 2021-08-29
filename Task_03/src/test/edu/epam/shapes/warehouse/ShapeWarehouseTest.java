package test.edu.epam.shapes.warehouse;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.observer.impl.TriangleObserver;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.warehouse.Warehouse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ShapeWarehouseTest {
    private Warehouse warehouse;
    private CalculateService service;
    private ShapeFactory factory;
    @BeforeClass
    public void setUp(){
        warehouse = Warehouse.getInstance();
        service = new CalculateServiceImpl();
        factory = new ShapeFactory();
    }
    @AfterClass
    public void tearDown(){
        warehouse = null;
        service = null;
        factory = null;
    }
    @Test
    public void warehouseTest() throws ShapeException {
        Triangle triangle = factory.createTriangle(new double[]{0, 0, 4, 0, 0, 3});
        triangle.attach(new TriangleObserver());
        double perimeter = service.perimeter(triangle);
        double square = service.square(triangle);
        long id = triangle.getId();
        warehouse.addParameters(id, perimeter, square);
        double expectedPerimeter = 12;
        double actualPerimeter = warehouse.getParameters(id).getPerimeter();
        assertEquals(actualPerimeter, expectedPerimeter);
    }
}
