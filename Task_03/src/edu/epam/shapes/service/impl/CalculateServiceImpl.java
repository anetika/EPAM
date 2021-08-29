package edu.epam.shapes.service.impl;
import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CalculateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculateServiceImpl implements CalculateService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public double square(Triangle triangle){
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double height = Math.abs(triangle.getVertexPoint().getY() - triangle.getFirstBasePoint().getY());
        logger.info("Square of triangle was calculated");
        return 0.5 * base * height;
    }

    @Override
    public double perimeter(Triangle triangle){
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double firstSide = calculateSide(triangle.getFirstBasePoint(), triangle.getVertexPoint());
        double secondSide = calculateSide(triangle.getSecondBasePoint(), triangle.getVertexPoint());
        logger.info("Perimeter of triangle was calculated");
        return base + firstSide + secondSide;
    }

    private double calculateSide(Point a, Point b){
        double result = Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
        return result;
    }
}
