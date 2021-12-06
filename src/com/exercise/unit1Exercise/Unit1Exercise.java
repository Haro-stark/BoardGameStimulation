package com.exercise.unit1Exercise;

import com.exercise.Condition;
import com.exercise.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Unit1Exercise {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 54),
                new Person("Tom", "Cruise", 64),
                new Person("Ali", "Haider", 30)
        );
        // 1. Sort list by last name

        /*This method is without lambda. We are defining inline definition of interface abstract method here.*/
        /*Method 1*/
//        System.out.println("\nSorting All Names");
//        Collections.sort(people, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });

        /*With lambda.*/
        System.out.println("\nLambda Method: Sorting All Names");
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        printAll(people);
        // 2. create a method that prints all elements in the list
        // Method 1
        System.out.println("\nPrinting All People");
        printAll(people);

        /*With Lambda*/
        System.out.println("\nLambda Method: Printing All People");
        printConditionally(people, p -> true);

        // 3. create a method that prints all elements in list whose name starts with 'C'
        System.out.println("\nPrinting Names starting with C");
        printWithLastNameC(people);
        // Method 2
        System.out.println("\nPrinting On the basis of Last Name Condition:");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        /*With Lambda*/
        System.out.println("\nLambda Method: Printing On the basis of Last Name Condition");
        printConditionally(people, (p) -> p.getLastName().startsWith("C"));

    }

    private static void printConditionally(List<Person> people, Condition condition) {
        for (Person p : people) {
            if (condition.test(p))
                System.out.println(p);
        }
    }

    private static void printWithLastNameC(List<Person> people) {
        for (Person p : people) {
            if (p.getLastName().startsWith("c") || p.getLastName().startsWith("C"))
                System.out.println(p);
        }
    }

    private static void printAll(List<Person> people) {
        for (Person p : people) {
            System.out.println(p);
        }
    }
}
