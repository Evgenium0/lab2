package org.example;

public class Main {
    public static void main(String[] args) {
        //Добавление и создание фигур

        Figure circle = new Circle(5,0,0); // Создание фигуры со значениями
        Figure rectangle = new Rectangle(3,4); // Создание фигуры со значениями
        Figure triangle = new Triangle(3,4,5); // Создание фигуры со значениями
        Figure ellipse = new Ellipse(5,3); // Создание фигуры со значениями

        System.out.println("Circle area: " + circle.getArea()); // Вывод площади фигуры на экран после выполнения компиляции
        System.out.println("Circle perimeter: " + circle.gerPerimeter()); // Вывод периметра фигуры на экран после выполнения компиляции

        System.out.println("Rectangle area: " + rectangle.getArea()); // Вывод площади фигуры на экран после выполнения компиляции
        System.out.println("Rectangle perimeter: " + rectangle.gerPerimeter());//Вывод периметра фигуры на экран после выполнения компиляции

        System.out.println("Triangle area: " + triangle.getArea());   // Вывод площади фигуры на экран после выполнения компиляции
        System.out.println("Triangle perimeter: " + triangle.gerPerimeter()); // Вывод периметра фигуры на экран после выполнения компиляции

        System.out.println("Ellipse area: " + ellipse.getArea());// Вывод площади фигуры на экран после выполнения компиляции
        System.out.println("Ellipse perimeter: " + ellipse.gerPerimeter());// Вывод периметра фигуры на экран после выполнения компиляции
    }
}