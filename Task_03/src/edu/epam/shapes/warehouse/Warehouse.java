package edu.epam.shapes.warehouse;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger();
    private static final Warehouse instance = new Warehouse();
    private final Map<Long, TriangleParameter> triangleParameterMap = new HashMap<>();

    private Warehouse(){

    }

    public static Warehouse getInstance(){
        return instance;
    }

    public void addParameters(long id, double perimeter, double square){
        TriangleParameter data = new TriangleParameter();
        data.setPerimeter(perimeter);
        data.setSquare(square);
        triangleParameterMap.put(id, data);
    }

    public TriangleParameter getParameters(long id) throws ShapeException {
        TriangleParameter data = triangleParameterMap.get(id);
        if (data == null){
            logger.error("No such element in warehouse");
            throw new ShapeException("No such element in warehouse");
        }
        return data;
    }

    public void updateParameter(long id, double perimeter, double square) throws ShapeException {
        TriangleParameter data = triangleParameterMap.get(id);
        if (data == null){
            logger.error("No such element in warehouse");
            throw new ShapeException("No such element in warehouse");
        }
        data.setPerimeter(perimeter);
        data.setSquare(square);
        logger.info("Parameter with id" + id + "was updated");
    }

    public boolean containsKey(Long key){
        return triangleParameterMap.containsKey(key);
    }
}
