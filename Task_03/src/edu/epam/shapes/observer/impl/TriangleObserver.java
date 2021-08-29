package edu.epam.shapes.observer.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.observer.Observer;
import edu.epam.shapes.observer.TriangleEvent;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleObserver implements Observer {
    private static final Logger logger = LogManager.getLogger();
    private final CalculateService service = new CalculateServiceImpl();
    public void parameterChanged(TriangleEvent event) throws ShapeException {
        Triangle triangle = event.getSource();
        double perimeter = service.perimeter(triangle);
        double square = service.square(triangle);
        long id = triangle.getId();
        try {
            Warehouse.getInstance().updateParameter(id, perimeter, square);
        } catch (ShapeException e){
            logger.error("No such element in warehouse");
            throw new ShapeException("No such element in warehouse");
        }
    }
}
