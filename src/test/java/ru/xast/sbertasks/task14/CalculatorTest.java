package ru.xast.sbertasks.task14;

class CalculatorTest {
    public static void main(String[] args) {
        Source source = new DataProviderPSQL();
        Calculator calculator = new Calculator(source);

        calculator.fibonachi(10);

        calculator.fibonachi(10);

        calculator.fibonachi(15);
    }

}