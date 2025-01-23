package ru.xast.sbertasks.task15;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Calculator calculator = context.getBean(Calculator.class);
        calculator.fibonachi(10);
    }
}
