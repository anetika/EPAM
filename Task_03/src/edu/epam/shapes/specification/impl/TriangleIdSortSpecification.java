package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;

import java.util.Comparator;

public class TriangleIdSortSpecification implements Comparator<Triangle> {
    @Override
    public int compare(Triangle triangle1, Triangle triangle2){
        return Long.compare(triangle1.getId(), triangle2.getId());
    }
}
