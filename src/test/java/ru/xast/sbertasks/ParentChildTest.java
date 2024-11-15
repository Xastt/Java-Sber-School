package ru.xast.sbertasks;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParentChildTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testChildCreationWithoutParams() {
        new Child();
        String expectedOutput =
                "Parent:static 1n" +
                        "Parent:static 2n" +
                        "Child:static 1n" +
                        "Child:static 2n" +
                        "Parent:instance 1n" +
                        "Parent:instance 2n" +
                        "Parent:constructorn" +
                        "Child:instance 1n" +
                        "Child:instance 2n" +
                        "Child:constructorn";
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("r", ""));
    }

    @Test
    public void testChildCreationWithParams() {
        new Child("Ivan");
        String expectedOutput =
                "Parent:instance 1n" +
                        "Parent:instance 2n" +
                        "Parent:name-constructorn" +
                        "Child:instance 1n" +
                        "Child:instance 2n" +
                        "Child:name-constructorn";
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("r", ""));
    }
}

