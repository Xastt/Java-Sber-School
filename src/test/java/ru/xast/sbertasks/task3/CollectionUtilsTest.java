package ru.xast.sbertasks.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.xast.sbertasks.task2.SecondTask.PhoneNumber;
import ru.xast.sbertasks.task3.SecondTask.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for test work CollectionUtils
 * @author Artyom Khasrovyan
 * @see CollectionUtils
 */
public class CollectionUtilsTest {

    private List<Integer> sourceList;
    private List<Number> destinationList;
    private List<Integer> addList;

    @BeforeEach
    public void setUp() {
        sourceList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        destinationList = new ArrayList<>();
        addList = new ArrayList<>(Arrays.asList(3, 4, 5));
    }

    @Test
    public void testAddAll() {
        CollectionUtils.addAll(sourceList, destinationList);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), destinationList);
    }

    @Test
    public void testNewArrayList() {
        List<String> newList = CollectionUtils.newArrayList();
        assertTrue(newList.isEmpty());
    }

    @Test
    public void testIndexOf() {
        int index = CollectionUtils.indexOf(sourceList, 3);
        assertEquals(2, index);
    }

    @Test
    public void testLimit() {
        List<Integer> limitedList = CollectionUtils.limit(sourceList, 3);
        assertEquals(Arrays.asList(1, 2, 3), limitedList);
    }

    @Test
    public void testAdd() {
        CollectionUtils.add(destinationList, 10);
        assertTrue(destinationList.contains(10));
    }

    @Test
    public void testRemoveAll() {
        destinationList.addAll(Arrays.asList(1, 2, 3, 4));
        CollectionUtils.removeAll(destinationList, addList);
        assertEquals(Arrays.asList(1, 2), destinationList);
    }

    @Test
    public void testContainsAll() {
        destinationList.addAll(Arrays.asList(3, 4));
        boolean allContained = CollectionUtils.containsAll(destinationList, addList);
        assertFalse(allContained);
    }

    @Test
    public void testContainsAny() {
        destinationList.addAll(Arrays.asList(1, 2, 4));
        boolean anyContained = CollectionUtils.containsAny(destinationList, addList);
        assertTrue(anyContained);
    }

    @Test
    public void testRange() {
        List<Integer> rangeResult = CollectionUtils.range(sourceList, 2, 4);
        assertEquals(Arrays.asList(2, 3, 4), rangeResult);
    }

    @Test
    public void testRangeWithComparator() {
        List<Integer> rangeResultWithComparator = CollectionUtils.range(sourceList, 2, 4, Comparator.naturalOrder());
        assertEquals(Arrays.asList(2, 3, 4), rangeResultWithComparator);
    }
}

