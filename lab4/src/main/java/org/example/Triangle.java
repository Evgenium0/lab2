package org.example;

public class Triangle extends Figure {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c){
        // Объявление значений для фигуры
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double getArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double gerPerimeter() {
        return a + b + c;
    }
}
