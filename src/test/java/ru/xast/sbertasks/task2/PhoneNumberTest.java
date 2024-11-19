package ru.xast.sbertasks.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.xast.sbertasks.task1.Child;
import ru.xast.sbertasks.task1.Parent;
import ru.xast.sbertasks.task2.SecondTask.PhoneNumber;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class for test work PhoneNumber
 * @author Artyom Khasrovyan
 * @see PhoneNumber
 */
public class PhoneNumberTest {

    private PhoneNumber phoneNumber;

    @BeforeEach
    public void setUp() {
        phoneNumber = new PhoneNumber();
    }

    @Test
    public void testAddNewNumber() {
        phoneNumber.add("Иванов", "88005553535");
        List<String> numbers = phoneNumber.get("Иванов");
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains("88005553535"));
    }

    @Test
    public void testAddNewSurname() {
        phoneNumber.add("Сидоров", "88000003030");
        List<String> numbers = phoneNumber.get("Сидоров");
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains("88000003030"));
    }

    @Test
    public void testAddDuplicateNumber() {
        phoneNumber.add("Смирнов", "88006663636");
        phoneNumber.add("Смирнов", "88006663636");
        List<String> numbers = phoneNumber.get("Смирнов");
        assertEquals(1, numbers.size());
    }

    @Test
    public void testAddMultipleNumbersForSameSurname() {
        phoneNumber.add("Петров", "88007773737");
        phoneNumber.add("Петров", "88008883838");
        List<String> numbers = phoneNumber.get("Петров");
        assertEquals(2, numbers.size());
        assertTrue(numbers.contains("88007773737"));
        assertTrue(numbers.contains("88008883838"));
    }

    @Test
    public void testGetExistingSurname() {
        phoneNumber.add("Бобров", "88001113131");
        List<String> numbers = phoneNumber.get("Бобров");
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains("88001113131"));
    }

    @Test
    public void testGetNotExistingSurname() {
        List<String> numbers = phoneNumber.get("Jackie");
        assertTrue(numbers.isEmpty());
    }
}
