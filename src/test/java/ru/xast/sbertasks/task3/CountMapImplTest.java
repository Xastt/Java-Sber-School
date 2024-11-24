package ru.xast.sbertasks.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.xast.sbertasks.task3.FirstTask.CountMap;
import ru.xast.sbertasks.task3.FirstTask.CountMapImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountMapImplTest {

    private CountMap<Integer> countMap;

    @BeforeEach
    void setUp() {
        countMap = new CountMapImpl<>();
    }

    @Test
    void testAddAndGetCount() {
        countMap.add(1);
        countMap.add(1);
        countMap.add(2);

        assertEquals(2, countMap.getCount(1));
        assertEquals(1, countMap.getCount(2));
    }

    @Test
    void testRemove() {
        countMap.add(1);
        countMap.add(1);
        countMap.add(2);

        int countBeforeRemove = countMap.remove(1);
        assertEquals(2, countBeforeRemove);

        assertEquals(0, countMap.getCount(1));

    }

    @Test
    void testSize() {
        countMap.add(1);
        countMap.add(2);
        countMap.add(2);

        assertEquals(2, countMap.size()); //2 уникальных элемента
    }

    @Test
    void testAddAll() {
        CountMap<Integer> anotherCountMap = new CountMapImpl<>();
        anotherCountMap.add(1);
        anotherCountMap.add(2);
        anotherCountMap.add(2);

        countMap.addAll(anotherCountMap);

        assertEquals(1, countMap.getCount(1));
        assertEquals(2, countMap.getCount(2));
    }

    @Test
    void testToMap() {
        countMap.add(1);
        countMap.add(2);
        countMap.add(2);

        Map<Integer, Integer> map = countMap.toMap();

        assertEquals(2, map.get(2));
        assertEquals(1, map.get(1));
    }

    @Test
    void testToMapWithDestination() {
        Map<Integer, Integer> destination = new HashMap<>();

        countMap.add(1);
        countMap.add(2);

        countMap.toMap(destination);

        assertEquals(1, destination.get(1));
        assertEquals(1, destination.get(2));
    }
}
