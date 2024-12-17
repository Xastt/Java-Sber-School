package ru.xast.sbertasks.task10;

import org.junit.jupiter.api.Test;


class FactorialCalculatorTest {

    FactorialCalculator fc = new FactorialCalculator();

    @Test
    void readAndCalculate() {
        fc.readAndCalculate("src/test/resources/factorial.txt");
    }
}