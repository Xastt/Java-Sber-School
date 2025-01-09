package ru.xast.sbertasks.task10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * class which prints out numbers and letters
 * using multithreading without synchronized blocks.
 * Synchronization is working with the help of AtomicInteger class
 * @author Khasrovyan Artyom
 */
public class DigitNumOutput {

    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghij";

    public void startThreadWorking() {

        /*
        use instead synchronized block's
         */
        AtomicInteger counter = new AtomicInteger(0);

        /*
        first thread which print numbers
         */
        Thread numberThread = new Thread(() -> {
            for (int i = 0; i < NUMBERS.length(); i++) {
                System.out.print(NUMBERS.charAt(i));
                counter.incrementAndGet();
                try {
                    if (counter.get() < (NUMBERS.length() + LETTERS.length())) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        /*
        second thread which print letters
         */
        Thread letterThread = new Thread(() -> {
            for (int i = 0; i < LETTERS.length(); i++) {
                System.out.print(LETTERS.charAt(i));
                counter.incrementAndGet();
                try {
                    if (counter.get() < (NUMBERS.length() + LETTERS.length())) {
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        /*
        start work of all threads
         */
        numberThread.start();
        letterThread.start();

        /*
        synchronize thread working
         */
        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

