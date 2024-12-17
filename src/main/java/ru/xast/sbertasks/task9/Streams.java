package ru.xast.sbertasks.task9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * class which realize all stream methods
 * @param <T>
 * @author Khasrovyan Artyom
 */
public class Streams<T> {

    private final List<T> collection;

    /**
     * create copy of current collection
     * @param collection
     */
    private Streams(List<T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    /**
     * helps create new Stream object, use list as source
     * @param list
     * @return new Stream
     * @param <T>
     */
    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    /**
     * @param predicate
     * @return new Streams object with filtered collection
     */
    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> filtered = collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return new Streams<>(filtered);
    }

    /**
     * @param mapper
     * @return new Streams object with transformed collection
     * @param <R>
     */
    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        List<R> transformed = collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new Streams<>(transformed);
    }

    /**
     *
     * @param keyMapper
     * @param valueMapper
     * @return map collection
     * @param <K>
     * @param <V>
     */
    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends V> valueMapper) {
        return collection.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }
}

