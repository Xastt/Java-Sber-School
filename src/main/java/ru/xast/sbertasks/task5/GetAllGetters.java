package ru.xast.sbertasks.task5;

import java.lang.reflect.Method;

/**
 * Class for getting all getters
 * @author Khasrovyan Artyom
 */
public class GetAllGetters {

    public static void main(String[] args) {
        Class calcClass = CalculatorImpl.class;
        Method[] methods = calcClass.getDeclaredMethods();
        for (Method method : methods) {
            if(checkGet(method)){
                System.out.println(method.getName());
            }
        }
    }

    private static boolean checkGet(Method method){
        return method.getName().startsWith("get");
    }
}
