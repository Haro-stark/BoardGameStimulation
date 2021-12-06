package com.exercise.unit2Exercise;

import com.exercise.Condition;
import com.exercise.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

// will be using java builtin Functional Interfaces for lambda expressions in this unit 2 exercise
public class Unit2Exercise {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 54),
                new Person("Tom", "Cruise", 64),
                new Person("Ali", "Haider", 30)
        );
        // 1. Sort list by last name


        /*With lambda.*/
        System.out.println("\nLambda Method: Sorting All Names");
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        printAll(people);
        // 2. create a method that prints all elements in the l
        /*With Lambda*/
        System.out.println("\nLambda Method: Printing All People");
        printConditionally(people, p -> true, p->p.getAge());

        // 3. create a method that prints all elements in list whose name starts with 'C'
        System.out.println("\nPrinting Names starting with C");
        printWithLastNameC(people);
        // Method 2
        System.out.println("\nPrinting On the basis of Last Name Condition:");
//        printConditionally(people, new Condition() {
//            @Override
//            public boolean test(Person p) {
//                return p.getLastName().startsWith("C");
//            }
//        });

        /*With Lambda*/
        System.out.println("\nLambda Method: Printing On the basis of Last Name Condition");
        printConditionally(people, (p) -> p.getLastName().startsWith("C"), p-> System.out.println(p));

    }

    private static void printConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer){
        for (Person s : people) {
            if (condition.test(s))
                consumer.accept(s);
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
