package ru.xast.sbertasks.task6;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing how does ArrayIterator works
 * @author Khasrovyan Artyom
 * @see ArrayIterator
 */
public class ArrayIteratorTest {

    @Test
    public void testArrayIterator() {
        Integer[] array = {1,2,3,4};
        ArrayIterator<Integer> iterator = new ArrayIterator<>(array);

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next());

        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
