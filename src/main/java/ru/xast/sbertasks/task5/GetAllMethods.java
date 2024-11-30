package ru.xast.sbertasks.task5;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetAllMethods {
    public static void main(String[] args) {
        // Выводим имя метода и его модификаторы
        Class calcImplClass = CalculatorImpl.class;
        Method[] methods = calcImplClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getName());
        }
        // Выводим методы родительского класса
        Class supClass = calcImplClass.getSuperclass();
        while (supClass != null) {
            for(Method method : supClass.getDeclaredMethods()) {
                System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getName());
            }
            supClass = supClass.getSuperclass();
        }
    }
}
