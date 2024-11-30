package ru.xast.sbertasks.task5;

public class CalculatorImpl implements Calculator {

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAYY = "WEDNESDAY";

    private int number;

    public CalculatorImpl(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int calc(int number) {
        if (number < 0) {
                throw new ArithmeticException("Number should be positive");
        }
        return factorial(number);
    }

    private int factorial(int number) {
        if(number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
