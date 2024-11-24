package ru.xast.sbertasks.task3.FirstTask;

import java.util.Map;

/**
 * parameterized interface for working with Map
 * @author Khasrovyan Artyom
 * @see CountMapImpl
 * @see Main
 */
public interface CountMap<T> {

    /**
     * Add new element to container
     * @param o
     */
    void add(T o);

    /**
     * @param o
     * @return number of additions
     */
    int getCount(T o);

    /**
     * remove element from container
     * @param o
     * @return number of additions(before remove)
     */
    int remove(T o);

    /**
     * @return number of elements
     */
    int size();

    /**
     * add all elements from source to the current container
     * if the keys are similar - sum their values
     * @param source
     */
    void addAll(CountMap<? extends T> source);

    /**
     * @return java.util.Map key - added element
     * value - number of additions
     */
    Map<T, Integer> toMap();

    /**
     * works like toMap, but all information records in destination
     * @param destination
     */
    void toMap(Map<? super T, Integer> destination);

    /**
     * print all elements
     */
    void printElements();
}
