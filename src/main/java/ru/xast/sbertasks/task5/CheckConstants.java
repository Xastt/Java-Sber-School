package ru.xast.sbertasks.task5;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * class for checking if constants equals their names
 * @author Khasrovyan Artyom
 */
public class CheckConstants {
    public static void main(String[] args) {
        checkConstants(CalculatorImpl.class);
    }

    public static void checkConstants(Class c) {
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType() == String.class && Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) {
                try{
                    String value = (String)f.get(null);
                    if (!f.getName().equals(value)) {
                        System.out.println("Константа " + f.getName() + " не соответствует своему имени.");
                   }
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
