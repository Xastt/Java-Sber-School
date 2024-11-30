package ru.xast.sbertasks.task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CashProxy implements InvocationHandler {
    private final Object target;
    private final Map<Integer, Integer> cache = new HashMap<>();

    public CashProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("calc")) {
            int number = (Integer) args[0];
            return cache.computeIfAbsent(number, n -> {
                try {
                    return (Integer) method.invoke(target, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return method.invoke(target, args);
    }
}