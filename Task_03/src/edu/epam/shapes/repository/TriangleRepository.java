package edu.epam.shapes.repository;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.specification.TriangleSpecification;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface TriangleRepository {
    void add(Triangle triangle);
    void addAll(List<Triangle> triangles);
    void remove(Triangle triangle);
    void removeAll(List<Triangle> triangles);
    void clear();
    void set(int index, Triangle triangle);
    Triangle get(int index);
    List<Triangle> sort(Comparator<Triangle> comparator);
    List<Triangle> query(TriangleSpecification specification);
    List<Triangle> queryStream(TriangleSpecification specification);
    List<Triangle> queryPredicate(Predicate<Triangle> predicate);
}
