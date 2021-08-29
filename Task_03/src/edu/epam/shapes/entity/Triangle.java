package edu.epam.shapes.entity;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.observer.Observable;
import edu.epam.shapes.observer.Observer;
import edu.epam.shapes.observer.TriangleEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Triangle implements Observable {
    private static final Logger logger = LogManager.getLogger();
    private final long id;
    private Point firstBasePoint;
    private Point secondBasePoint;
    private Point vertexPoint;
    private List<Observer> observerList = new ArrayList<>();

    public Triangle(long id, Point firstBasePoint, Point secondBasePoint, Point vertexPoint){
        this.id = id;
        this.firstBasePoint = firstBasePoint;
        this.secondBasePoint = secondBasePoint;
        this.vertexPoint = vertexPoint;
    }

    public long getId(){
        return id;
    }

    public Point getFirstBasePoint(){
        return firstBasePoint;
    }

    public void setFirstBasePoint(Point firstBasePoint){
        this.firstBasePoint = firstBasePoint;
    }

    public Point getSecondBasePoint(){
        return secondBasePoint;
    }

    public void setSecondBasePoint(Point secondBasePoint){
        this.secondBasePoint = secondBasePoint;
    }

    public Point getVertexPoint(){
        return vertexPoint;
    }

    public void setVertexPoint(Point vertexPoint){
        this.vertexPoint = vertexPoint;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Triangle{ ");
        builder.append("Id : ").append(id).append(" ");
        builder.append("firstBasePoint : ").append(firstBasePoint).append(" ");
        builder.append("secondBasePoint : ").append(secondBasePoint).append(" ");
        builder.append("vertexPoint : ").append(vertexPoint).append(" ");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return id == triangle.id &&
                firstBasePoint.equals(triangle.firstBasePoint) &&
                secondBasePoint.equals(triangle.secondBasePoint) &&
                vertexPoint.equals(triangle.vertexPoint);

    }

    @Override
    public int hashCode() {
        return Long.hashCode(id)
                + firstBasePoint.hashCode()
                + secondBasePoint.hashCode()
                + vertexPoint.hashCode();
    }

    @Override
    public void attach(Observer observer) throws ShapeException {
        if (observer != null){
            observerList.add(observer);
        }else{
            logger.error("Observer is null");
            throw new ShapeException("Observer is null");
        }
    }

    @Override
    public void detach(Observer observer) throws ShapeException {
        if (observer != null){
            observerList.remove(observer);
        }else{
            logger.error("Observer is null");
            throw new ShapeException("Observer is null");
        }
    }

    @Override
    public void notifyObservers() throws ShapeException {
        if (!observerList.isEmpty()){
            TriangleEvent event = new TriangleEvent(this);
            for (Observer observer : observerList){
                observer.parameterChanged(event);
            }
        }
    }
}
