package test.edu.epam.shapes.repository;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.repository.TriangleRepository;
import edu.epam.shapes.repository.impl.TriangleRepositoryImpl;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.specification.impl.TrianglePerimeterSortSpecification;
import edu.epam.shapes.specification.impl.TrianglePerimeterSpecification;
import edu.epam.shapes.specification.impl.TriangleSquareSortSpecification;
import edu.epam.shapes.specification.impl.TriangleSquareSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ShapeRepositoryTest {
    private TriangleRepository repository;
    private List<Triangle> perimeterList;
    private List<Triangle> perimeterList1;
    private List<Triangle> squareList;
    private List<Triangle> squareList1;

    @BeforeClass
    public void setUp(){
        repository = TriangleRepositoryImpl.getInstance();
        Triangle triangle1 = new Triangle(1, new Point(0,0), new Point(2, 0), new Point(0, 2));
        Triangle triangle2 = new Triangle(2, new Point(0, 0), new Point(4, 0), new Point(0, 3));
        repository.add(triangle1);
        repository.add(triangle2);
        perimeterList = new ArrayList<>();
        perimeterList.add(triangle1);
        perimeterList.add(triangle2);
        perimeterList1 = new ArrayList<>();
        perimeterList1.add(triangle2);
        squareList = new ArrayList<>();
        squareList.add(triangle1);
        squareList.add(triangle2);
        squareList1 = new ArrayList<>();
        squareList1.add(triangle2);
    }

    @AfterClass
    public void tearDown(){
        repository = null;
    }

    @Test
    public void sortPerimeterTestTrue() {
        List<Triangle> actual = repository.sort(new TrianglePerimeterSortSpecification());
        assertEquals(actual, perimeterList);
    }

    @Test
    public void sortSquareTestTrue() {
        List<Triangle> actual = repository.sort(new TriangleSquareSortSpecification());
        assertEquals(actual, squareList);
    }

    @Test
    public void queryPerimeterTestTrue() {
        List<Triangle> actual = repository.query(new TrianglePerimeterSpecification(12));
        assertEquals(actual, perimeterList1);
    }

    @Test
    public void queryStreamSquareTestTrue() {
        List<Triangle> actual = repository.queryStream(new TriangleSquareSpecification(6));
        assertEquals(actual, squareList1);
    }

    @Test
    public void queryPredicateSquareTestTrue() {
        CalculateService service = new CalculateServiceImpl();
        List<Triangle> actual = repository.queryPredicate(triangle -> service.square(triangle) == 6);
        assertEquals(actual, squareList1);
    }
}
