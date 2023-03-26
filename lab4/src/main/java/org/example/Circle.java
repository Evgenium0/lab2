package org.example;

public class Circle extends Figure {
    private double radius;
    private double centerX;
    private double centerY;

    public Circle(double radius, double centerX, double centerY){
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double gerPerimeter() {
        return 2 * Math.PI * radius;
    }
}
