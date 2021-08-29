package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;

import java.util.Comparator;

public class TrianglePerimeterSortSpecification implements Comparator<Triangle> {
    private static final CalculateService service = new CalculateServiceImpl();
    @Override
    public int compare(Triangle triangle1, Triangle triangle2){
        double perimeter1 = service.perimeter(triangle1);
        double perimeter2 = service.perimeter(triangle2);
        return Double.compare(perimeter1, perimeter2);
    }
}
