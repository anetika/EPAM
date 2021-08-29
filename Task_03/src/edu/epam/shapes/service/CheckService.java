package edu.epam.shapes.service;

import edu.epam.shapes.entity.Triangle;

public interface CheckService {
    boolean isTriangle(double[] array);
    boolean isRightTriangle(Triangle triangle);
    boolean isIsoscelesTriangle(Triangle triangle);
    boolean isEquilateralTriangle(Triangle triangle);
    boolean isObtuseTriangle(Triangle triangle);
    boolean isAcuteAngledTriangle(Triangle triangle);
}
