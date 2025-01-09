package ru.xast.sbertasks.task10;

import java.io.*;
import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * program calculates factorial of numbers
 * using multithreading
 * @author Khasrovyan Artyom
 */
public class FactorialCalculator {

    public void readAndCalculate(String filePath) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                executor.submit(() -> {
                    BigInteger factorial = calculateFactorial(number);
                    System.out.println("Factorial " + number + " = " + factorial);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
