package org.example;

public class Ellipse extends Figure{
    private double maxAxis;
    public  double minAxis;

    public Ellipse(double maxAxis, double minAxis){
        // Объявление значений для фигуры
        this.maxAxis = maxAxis;
        this.minAxis = minAxis;
    }
    @Override
    public double getArea() {
        return Math.PI * maxAxis * minAxis;
    }

    @Override
    public double gerPerimeter() {
        return 2 * Math.PI * Math.sqrt((maxAxis * maxAxis + minAxis * minAxis) / 2);
    }
}
