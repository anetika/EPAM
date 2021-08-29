package edu.epam.shapes.service;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.exception.ShapeException;

public interface CalculateService {
    double square(Triangle triangle);
    double perimeter(Triangle triangle);
}
