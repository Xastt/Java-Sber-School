package ru.xast.sbertasks.task10;

import java.util.concurrent.atomic.AtomicInteger;

public class DigitNumOutput {
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghij";

    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger(0);

        Thread numberThread = new Thread(() -> {
            for (int i = 0; i < NUMBERS.length(); i++) {
                System.out.print(NUMBERS.charAt(i));
                counter.incrementAndGet();
                try {
                    // Условие для управления выводом
                    if (counter.get() < (NUMBERS.length() + LETTERS.length())) {
                        Thread.sleep(1); // Задержка для имитации работы потока
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread letterThread = new Thread(() -> {
            for (int i = 0; i < LETTERS.length(); i++) {
                System.out.print(LETTERS.charAt(i));
                counter.incrementAndGet();
                try {
                    // Задержка для имитации работы потока
                    if (counter.get() < (NUMBERS.length() + LETTERS.length())) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        numberThread.start();
        letterThread.start();

        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

