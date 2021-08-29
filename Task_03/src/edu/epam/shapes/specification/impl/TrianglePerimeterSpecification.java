package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.specification.TriangleSpecification;

public class TrianglePerimeterSpecification implements TriangleSpecification {
    private double perimeter;
    private static final CalculateService service = new CalculateServiceImpl();

    public TrianglePerimeterSpecification(double perimeter){
        this.perimeter = perimeter;
    }

    @Override
    public boolean specified(Triangle triangle){
        return perimeter == service.perimeter(triangle);
    }
}
