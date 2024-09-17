package org.example;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    // используем сортировку
    public static void main(String[] args) {
        Person person = new Person("Tom", 12);
        Set<Person> people = new TreeSet<>(Comparator.comparing(Person::age));

        people.add(person);
        people.add(new Person("Ivan", 5));
        people.add(new Person("Sveta", 30));
        people.add(new Person("Sveta2", 1));

        for (Person person1 : people) {
            System.out.println(person1.name() + " " + person1.age());
        }
    }
}