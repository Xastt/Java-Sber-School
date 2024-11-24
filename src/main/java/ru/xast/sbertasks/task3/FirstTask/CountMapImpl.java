package ru.xast.sbertasks.task3.FirstTask;

import java.util.HashMap;
import java.util.Map;

/**
 * class which implements CountMap metods
 * @param <T>
 * @author Khasrovyan Artyom
 * @see Main
 * @see CountMap
 */
public class CountMapImpl<T> implements CountMap<T> {

    private final Map<T, Integer> map = new HashMap<>();

    /**
     * Add new element to container
     * @param o
     */
    @Override
    public void add(T o) {
        map.put(o, map.getOrDefault(o, 0) + 1);
    }

    private void add(T o, int count) {
        map.put(o, map.getOrDefault(o, 0) + count);
    }

    /**
     * @param o
     * @return number of additions
     */
    @Override
    public int getCount(T o) {
        return map.getOrDefault(o, 0);
    }

    /**
     * remove element from container
     * @param o
     * @return number of additions(before remove)
     */
    @Override
    public int remove(T o) {
        return map.remove(o);
    }

    /**
     * @return number of elements
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * add all elements from source to the current container
     * if the keys are similar - sum their values
     * @param source
     */
    @Override
    public void addAll(CountMap<? extends T> source) {
        Map<? extends T, Integer> sourceMap = source.toMap();
        for(T key : sourceMap.keySet()) {
            int count = sourceMap.get(key);
            add(key, count);
        }

    }

    /**
     * @return java.util.Map key - added element
     * value - number of additions
     */
    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    /**
     * works like toMap, but all information records in destination
     * @param destination
     */
    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(map);
    }

    /**
     * print all elements
     */
    @Override
    public void printElements() {
        for(Map.Entry<T, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
