package ru.xast.sbertasks.task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * class which check annotations and if method has annotation @Metric
 * when class will record how long does this method works
 * @see Metric
 * @author Khasrovyan Artyom
 */
public class PerfomanceProxy implements InvocationHandler {
    private Object target;

    public PerfomanceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Metric.class)){
            long startTime = System.nanoTime();
            Object result = method.invoke(target, args);
            long endTime = System.nanoTime();
            System.out.println("Время работы метода: " + (endTime - startTime) + " нс");
            return result;
        }
        return method.invoke(target, args);
    }
}
