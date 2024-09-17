package org.example;

import java.util.TreeSet;

//Присваеваем к объекту Person строку name и целочисленное значение age
public class Person {

    private String name;
    private Integer age;

// Объявляем в Person эти переменные
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Integer age() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }
}
