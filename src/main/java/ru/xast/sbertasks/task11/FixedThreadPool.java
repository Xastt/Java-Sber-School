package ru.xast.sbertasks.task11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final int nThreads;
    private final BlockingQueue<Runnable> queue;
    private final WorkerThread[] workers;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.queue = new ArrayBlockingQueue<Runnable>(nThreads);
        this.workers = new WorkerThread[nThreads];
    }

    /**
     * Starts thread's
     */
    @Override
    public void start() {
        for (int i = 0; i < nThreads; i++) {
            workers[i] = new WorkerThread(queue);
            workers[i].start();
        }
    }

    /**
     * Puts task's into the queue.
     * The thread must execute this task.
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        try{
            queue.put(runnable);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * For processing tasks in a multithreaded.
     * It used in a thread pool where several threads
     * can execute tasks from the same blocking queue.
     */
    private static class WorkerThread extends Thread {
        private final BlockingQueue<Runnable> queue;

        public WorkerThread(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) {
                try{
                    Runnable task = queue.take();
                    task.run();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
