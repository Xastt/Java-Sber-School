package ru.xast.sbertasks.task11;

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

        //wait until all tasks complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

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

        //wait until all tasks complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
