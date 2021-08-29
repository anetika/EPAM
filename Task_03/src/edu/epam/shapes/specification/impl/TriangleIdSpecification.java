package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.specification.TriangleSpecification;

public class TriangleIdSpecification implements TriangleSpecification {
    private long id;
    public TriangleIdSpecification(long id){
        this.id = id;
    }

    @Override
    public boolean specified(Triangle triangle){
        return triangle.getId() == id;
    }
}
