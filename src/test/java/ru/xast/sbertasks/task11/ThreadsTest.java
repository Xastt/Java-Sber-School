package ru.xast.sbertasks.task11;

import java.util.List;

public class ThreadsTest {
    public static void main(String[] args) {
        System.out.println("--------------FixedThreadPool--------------");

        ThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            fixedThreadPool.execute(() -> {
                System.out.println("FixedThreadPool do task " + taskId + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); //task simulation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        fixedThreadPool.shutdown();
        System.out.println("FixedThreadPool - shutdown") ;

        System.out.println("---------------ScalableThreadPool---------------");

        ThreadPool scalableThreadPool = new ScalableThreadPool(2, 5);
        scalableThreadPool.start();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            scalableThreadPool.execute(() -> {
                System.out.println("ScalableThreadPool do task " + taskId + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); //task simulation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scalableThreadPool.shutdown();
        System.out.println("ScalableThreadPool - shutdown");

        // Testing shutdownNow
        System.out.println("----Testing shutdownNow on ScalableThreadPool----");
        ThreadPool anotherScalableThreadPool = new ScalableThreadPool(2, 5);
        anotherScalableThreadPool.start();

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            anotherScalableThreadPool.execute(() -> {
                System.out.println("AnotherScalableThreadPool do task " + taskId + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); //task simulation
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            // Wait for some time to let some tasks start
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Now we will shut it down immediately
//        List<Runnable> remainingTasks = anotherScalableThreadPool.shutdownNow();
//        System.out.println("AnotherScalableThreadPool has been shut down immediately.");
//        System.out.println("Remaining tasks: " + remainingTasks.size());
    }
}
