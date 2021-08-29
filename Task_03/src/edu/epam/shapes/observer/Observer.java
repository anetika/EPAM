package edu.epam.shapes.observer;

import edu.epam.shapes.exception.ShapeException;

public interface Observer {
    void parameterChanged(TriangleEvent event) throws ShapeException;
}
