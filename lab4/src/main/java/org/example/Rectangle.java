package org.example;

public class Rectangle extends  Figure{
    private double wight;
    private  double height;

    public Rectangle(double wight, double height){
        // Объявление значений для фигуры
        this.wight = wight;
        this.height = height;
    }
    @Override
    public double getArea() {
        return wight * height;
    }

    @Override
    public double gerPerimeter() {
        return 2 * (wight * height);
    }




}
