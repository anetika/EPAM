package edu.epam.shapes.factory;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.service.CheckService;
import edu.epam.shapes.service.impl.CheckServiceImpl;
import edu.epam.shapes.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    private static final Logger logger = LogManager.getLogger();
    public List<Triangle> createTriangles(List<double[]> list) throws ShapeException {
        if (list == null){
            logger.error("List is null");
            throw new ShapeException("List is null");
        }
        if (list.isEmpty()){
            logger.error("List is empty");
            throw new ShapeException("List is empty");
        }
        List<Triangle> triangles = new ArrayList<>();
        for (double[] array : list){
            triangles.add(createTriangle(array));
        }

        return triangles;
    }
    public Triangle createTriangle(double[] array) throws ShapeException {
        if (array == null || array.length != 6){
            logger.error("Array is null or wrong length of array");
            throw new ShapeException("Array is null or wrong length of array");
        }
        CheckService service = new CheckServiceImpl();
        if (!service.isTriangle(array)){
            logger.error("Three points don't create a triangle");
            throw new ShapeException("Three points don't create a triangle");
        }
        long id = IdGenerator.generateId();
        Point point1 = new Point(array[0], array[1]);
        Point point2 = new Point(array[2], array[3]);
        Point point3 = new Point(array[4], array[5]);
        logger.info("Triangle is created");
        return new Triangle(id, point1, point2, point3);
    }
}
