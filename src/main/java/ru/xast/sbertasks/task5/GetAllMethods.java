package ru.xast.sbertasks.task5;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * class for getting all methods and their modifiers from classes and superclasses
 * @author Khasrovyan Artyom
 */
public class GetAllMethods {
    public static void main(String[] args) {

        Class calcImplClass = CalculatorImpl.class;
        Method[] methods = calcImplClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getName());
        }

        Class supClass = calcImplClass.getSuperclass();
        while (supClass != null) {
            for(Method method : supClass.getDeclaredMethods()) {
                System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getName());
            }
            supClass = supClass.getSuperclass();
        }
    }
}
