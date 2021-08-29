package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Triangle;
import edu.epam.shapes.service.CheckService;

public class CheckServiceImpl implements CheckService {

    @Override
    public boolean isTriangle(double[] array){
        boolean flag = true;
        double x_1 = array[0];
        double y_1 = array[1];
        double x_2 = array[2];
        double y_2 = array[3];
        double x_3 = array[4];
        double y_3 = array[5];
        double xPart = (x_1 - x_2) / (x_3 - x_2);
        double yPart = (y_1 - y_2) / (y_3 - y_2);
        if(xPart == yPart){
            flag = false;
        }
         return flag;
    }

    @Override
    public boolean isRightTriangle(Triangle triangle){
        boolean flag = false;
        if(triangle.getFirstBasePoint().getX() == triangle.getVertexPoint().getX() || triangle.getSecondBasePoint().getX() == triangle.getVertexPoint().getX()){
            flag = true;
        }
         return flag;
    }

    @Override
    public boolean isIsoscelesTriangle(Triangle triangle){
        boolean flag = false;
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double firstSide = calculateSide(triangle.getFirstBasePoint(), triangle.getVertexPoint());
        double secondSide = calculateSide(triangle.getSecondBasePoint(), triangle.getVertexPoint());
        if (base == firstSide || base == secondSide || firstSide == secondSide){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isEquilateralTriangle(Triangle triangle){
        boolean flag = false;
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double firstSide = calculateSide(triangle.getFirstBasePoint(), triangle.getVertexPoint());
        double secondSide = calculateSide(triangle.getSecondBasePoint(), triangle.getVertexPoint());
        if (base == firstSide && base == secondSide){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isObtuseTriangle(Triangle triangle){
        boolean flag = false;
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double firstSide = calculateSide(triangle.getFirstBasePoint(), triangle.getVertexPoint());
        double secondSide = calculateSide(triangle.getSecondBasePoint(), triangle.getVertexPoint());
        if (base > Math.hypot(firstSide, secondSide) || firstSide > Math.hypot(base, secondSide) || secondSide > Math.hypot(base, firstSide)){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isAcuteAngledTriangle(Triangle triangle){
        boolean flag = false;
        double base = Math.abs(triangle.getFirstBasePoint().getX() - triangle.getSecondBasePoint().getX());
        double firstSide = calculateSide(triangle.getFirstBasePoint(), triangle.getVertexPoint());
        double secondSide = calculateSide(triangle.getSecondBasePoint(), triangle.getVertexPoint());
        if (base < Math.hypot(firstSide, secondSide) && firstSide < Math.hypot(base, secondSide) && secondSide < Math.hypot(base, firstSide)){
            flag = true;
        }
        return flag;
    }

    private double calculateSide(Point a, Point b){
        double result = Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
        return result;
    }
}
