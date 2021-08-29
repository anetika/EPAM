package edu.epam.shapes.warehouse;

public class TriangleParameter {
    private double perimeter;
    private double square;

    public TriangleParameter(){
        this.perimeter = perimeter;
        this.square = square;
    }

    public double getPerimeter(){
        return perimeter;
    }

    public void setPerimeter(double perimeter){
        this.perimeter = perimeter;
    }

    public double getSquare(){
        return square;
    }

    public void setSquare(double square){
        this.square = square;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Triangle parameters{ ");
        builder.append("Perimeter: ").append(perimeter).append(" ");
        builder.append("Square: ").append(square).append(" ");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        TriangleParameter that = (TriangleParameter) o;
        return perimeter == that.perimeter && square == that.square;
    }

    @Override
    public int hashCode(){
        return Double.hashCode(perimeter) + Double.hashCode(square);
    }
}
