package edu.epam.shapes.repository.impl;

import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.repository.TriangleRepository;
import edu.epam.shapes.specification.TriangleSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TriangleRepositoryImpl implements TriangleRepository {
    private static final Logger logger = LogManager.getLogger();
    private static final TriangleRepositoryImpl instance = new TriangleRepositoryImpl();
    private final List<Triangle> list = new ArrayList<>();

    public static TriangleRepositoryImpl getInstance(){
        return instance;
    }

    @Override
    public void add(Triangle triangle){
        list.add(triangle);
        logger.info("Triangle was added to repository");
    }

    @Override
    public void addAll(List<Triangle> triangles){
        list.addAll(triangles);
        logger.info("All triangles were added to repository");
    }

    @Override
    public void remove(Triangle triangle){
        list.remove(triangle);
        logger.info("Triangle was removed from repository");
    }

    @Override
    public void removeAll(List<Triangle> triangles){
        list.removeAll(triangles);
        logger.info("All triangles were removed from repository");
    }

    @Override
    public void clear(){
        list.clear();
        logger.info("Repository is clear");
    }

    @Override
    public void set(int index, Triangle triangle){
        list.set(index, triangle);
    }

    @Override
    public Triangle get(int index){
        return list.get(index);
    }

    @Override
    public List<Triangle> sort(Comparator<Triangle> comparator){
        logger.info("Repository return sorted list");
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Triangle> query(TriangleSpecification specification){
        List<Triangle> result = new ArrayList<>();
        for (Triangle triangle : list){
            if (specification.specified(triangle)){
                result.add(triangle);
            }
        }
        logger.info("Sorted list was returned");
        return result;
    }

    @Override
    public List<Triangle> queryStream(TriangleSpecification specification){
        logger.info("Query was returned");
        return list.stream().filter(specification::specified).collect(Collectors.toList());
    }

    @Override
    public List<Triangle> queryPredicate(Predicate<Triangle> predicate){
        logger.info("Query was returned");
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
