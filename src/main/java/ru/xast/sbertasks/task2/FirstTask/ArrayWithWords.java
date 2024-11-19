package ru.xast.sbertasks.task2.FirstTask;

import java.util.*;

/**
 * Class in which I should: Create an array with
 * set of words. Find and output list of unique words
 * that make up the array. Count how many times each word occurs.
 * @author Khasrovyan Artyom
 */
public class ArrayWithWords {
    public static void main(String[] args) {
        List<String> countries = Arrays.asList("London", "Paris", "Tokyo", "Washington",
                "Berlin", "London", "Rome", "Madrid", "Tokyo", "Ottawa",
                "Paris", "Seoul", "Moscow", "Berlin", "London",
                "New Delhi", "Madrid", "Tokyo", "Rome", "Berlin");

        Set <String> uniqueCountries = new HashSet<>(countries);

        System.out.println("Массив данных:\n" + countries + "\n-----------");
        System.out.println("Массив уникальных данных:\n" + uniqueCountries + "\n-----------");
        System.out.println("Подсчет количества слов в массиве:");
        for(String key : uniqueCountries){
            System.out.print(key + ": " + Collections.frequency(countries, key) + " | ");
        }
    }
}
