package ru.xast.sbertasks.task14;

import java.util.*;

/**
 * Class for execution mathematical operations
 */
@SuppressWarnings("unchecked")
public class Calculator {

    private final Source source;

    public Calculator(Source source) {
        this.source = source;
    }

    /**
     * Calculates the Fibonacci sequence up to the specified number.
     * Results are cached to avoid repeated calculations.
     * @param n The number to which to calculate the Fibonacci sequence.
     * @return List of Fibonacci numbers.
     */
    @Cachable(DataProviderPSQL.class)
    public List<Integer> fibonachi(int n){
        String cacheKey = "fibonachi_" + n;
        Object cachedValue = source.get(cacheKey);
        if(cachedValue != null){
            System.out.println("From cache: " + cacheKey);
            return (List<Integer>) cachedValue;
        }

        List<Integer> result = new ArrayList<>();
        int a = 0, b = 1;
        for(int i = 0; i < n; i++){
            result.add(a + b);
            int next = a + b;
            a = b;
            b = next;
        }
        source.save(cacheKey, result);
        System.out.println("Saved to Cache: " + result);
        return result;
    }
}
