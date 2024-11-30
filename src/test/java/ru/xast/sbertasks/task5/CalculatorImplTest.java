package ru.xast.sbertasks.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorImplTest {

    @Test
    public void testFactorialOfZero() {
        CalculatorImpl calculator = new CalculatorImpl(0);
        assertEquals(1, calculator.calc(0), "Factorial of 0 should be 1");
    }

    @Test
    public void testFactorialOfOne() {
        CalculatorImpl calculator = new CalculatorImpl(1);
        assertEquals(1, calculator.calc(1), "Factorial of 1 should be 1");
    }

    @Test
    public void testFactorialOfTwo() {
        CalculatorImpl calculator = new CalculatorImpl(2);
        assertEquals(2, calculator.calc(2), "Factorial of 2 should be 2");
    }

    @Test
    public void testFactorialOfThree() {
        CalculatorImpl calculator = new CalculatorImpl(3);
        assertEquals(6, calculator.calc(3), "Factorial of 3 should be 6");
    }

    @Test
    public void testFactorialOfFour() {
        CalculatorImpl calculator = new CalculatorImpl(4);
        assertEquals(24, calculator.calc(4), "Factorial of 4 should be 24");
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        CalculatorImpl calculator = new CalculatorImpl(-1);
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calc(-1);
        });
        assertEquals("Number should be positive", exception.getMessage());
    }
}