package ru.xast.sbertasks.task5;

import java.lang.reflect.Method;

/**
 * class which copy all information from one class to another
 * @author Khasrovyan Artyom
 */
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should be
     * the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        if (to == null || from == null) {
            throw new IllegalArgumentException("Source and Target objects cannot be null");
        }

        // Get all methods from the source object
        Method[] getterMethods = from.getClass().getMethods();

        for (Method getter : getterMethods) {
            if (isGetter(getter)) {
                try {
                    Object value = getter.invoke(from); // Get value from source object
                    String propertyName = getPropertyName(getter);

                    // Find the corresponding setter in the target object
                    Method setter = findSetterMethod(to.getClass(), propertyName, value);
                    if (setter != null) {
                        setter.invoke(to, value); // Set value on target object
                    }
                } catch (Exception e) {
                    // Consider logging the exception or handling it based on requirements
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get") &&
                method.getParameterCount() == 0 &&
                !Void.TYPE.equals(method.getReturnType());
    }

    private static String getPropertyName(Method getter) {
        String methodName = getter.getName();
        return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
    }

    private static Method findSetterMethod(Class<?> clazz, String propertyName, Object value) {
        String setterName = "set" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                if (isCompatible(method.getParameterTypes()[0], value)) {
                    return method;
                }
            }
        }
        return null;
    }

    private static boolean isCompatible(Class<?> setterType, Object value) {
        return value == null || setterType.isAssignableFrom(value.getClass());
    }
}
