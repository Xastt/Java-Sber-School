package ru.xast.sbertasks.task5;

/**
 * interface for calculating factorial of numbers
 * @see CalculatorImpl
 * @author Khasrovyan Artyom
 */
public interface Calculator{
    /**
     * Расчет факториала числа.
     * @param number
     */
    int calc (int number);
}