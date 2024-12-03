package ru.xast.sbertasks.task6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class which iterate object array
 * @param <T>
 * @author Khasrovyan Artyom
 */
public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int index = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
