package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;

import java.util.Comparator;

public class TriangleSquareSortSpecification implements Comparator<Triangle> {
    private static final CalculateService service = new CalculateServiceImpl();

    @Override
    public int compare(Triangle triangle1, Triangle triangle2){
        double square1 = service.square(triangle1);
        double square2 = service.square(triangle2);
        return Double.compare(square1, square2);
    }
}
