package ru.xast.sbertasks.task3.SecondTask;

import ru.xast.sbertasks.task3.FirstTask.CountMapImpl;
import ru.xast.sbertasks.task3.FirstTask.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * parameterized class for working with List
 * @author Khasrovyan Artyom
 * @see Main
 */
public class CollectionUtils {

    /**
     * method, which add all elements from one connection to another
     * @param source
     * @param destination
     * @param <T>
     */
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    /**
     * method for creating new List
     * @return new ArrayList
     * @param <T>
     */
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * method for searching index of the element in the List
     * @param source
     * @param o
     * @return index
     * @param <T>
     */
    public static <T> int indexOf(List <? extends T> source, T o) {
        return source.indexOf(o);
    }

    /**
     * method which limit list size
     * @param source
     * @param size
     * @return new ArrayList
     * @param <T>
     */
    public static <T> List <T> limit(List <? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, Math.min(size, source.size())));
    }

    /**
     * method for adding new element in the list
     * @param source
     * @param o
     * @param <T>
     */
    public static <T> void add(List <? super T> source, T o) {
        source.add(o);
    }

    /**
     * method which remove all elements from one List to another
     * @param removeFrom
     * @param source
     * @param <T>
     */
    public static <T> void removeAll(List<? super T> removeFrom, List <? extends T> source) {
        for (T o : source) {
            removeFrom.remove(o);
        }
    }

    /**
     * method to check if all items from one list are present in another list
     * @param c1
     * @param c2
     * @return true/false
     * @param <T>
     */
    public static <T> boolean containsAll(List <? super T> c1, List<? extends T> c2) {
        for (T o : c2) {
            if (!c1.contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * method to check if any items from one list exist in another list
     * @param c1
     * @param c2
     * @return
     * @param <T>
     */
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T o : c2) {
            if (c1.contains(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for getting a sublist in a given range
     * @param list
     * @param min
     * @param max
     * @return result
     * @param <T>
     */
    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max) {
        List<T> result = new ArrayList<>();
        for (T o : list) {
            if (o.compareTo(min) >= 0 && o.compareTo(max) <= 0) {
                result.add(o);
            }
        }
        return result;
    }

    /**
     * Method for getting a sublist in a given range with a comparator
     * @param list
     * @param min
     * @param max
     * @param comparator
     * @return result
     * @param <T>
     */
    public static <T> List <T> range(List<T> list, T min, T max, Comparator <? super T> comparator) {
        List<T> result = new ArrayList<>();
        for (T o : list) {
            if(comparator.compare(o, min) >= 0 && comparator.compare(o, max) <= 0) {
                result.add(o);
            }
        }
        return result;
    }
}
