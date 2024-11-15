package ru.xast.sbertasks.task1;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Class for test the program
 * @author Artyom Khasrovyan
 * @see Parent
 * @see Child
 */
public class ParentChildTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Test method for checking the work of constructors
     */
    @Test
    public void testChildConstructors() {

        Child child = new Child();
        Child child2 = new Child("Ivan");
        String messageOut = outputStream.toString();

        assertTrue(messageOut.contains("Parent:static 1"));
        assertTrue(messageOut.contains("Parent:static 2"));
        assertTrue(messageOut.contains("Child:static 1"));
        assertTrue(messageOut.contains("Child:static 2"));
        assertTrue(messageOut.contains("Parent:instance 1"));
        assertTrue(messageOut.contains("Parent:instance 2"));
        assertTrue(messageOut.contains("Parent:constructor"));
        assertTrue(messageOut.contains("Child:instance 1"));
        assertTrue(messageOut.contains("Child:instance 2"));
        assertTrue(messageOut.contains("Child:constructor"));
        assertTrue(messageOut.contains("Parent:instance 1"));
        assertTrue(messageOut.contains("Parent:instance 2"));
        assertTrue(messageOut.contains("Parent:name-constructor"));
        assertTrue(messageOut.contains("Child:instance 1"));
        assertTrue(messageOut.contains("Child:instance 2"));
        assertTrue(messageOut.contains("Child:name-constructor"));

    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

}

