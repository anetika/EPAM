package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.specification.TriangleSpecification;

public class TriangleSquareSpecification implements TriangleSpecification {
    private double square;
    private static final CalculateService service = new CalculateServiceImpl();

    public TriangleSquareSpecification(double square){
        this.square = square;
    }

    @Override
    public boolean specified(Triangle triangle){
        return square == service.square(triangle);
    }
}
