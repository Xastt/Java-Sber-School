package ru.xast.sbertasks.task11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * class which realize Fixed Thread Pool
 * @author Khasrovyan Artyom
 */
public class FixedThreadPool implements ThreadPool {

    private final int nThreads;
    private final BlockingQueue<Runnable> queue;
    private final WorkerThread[] workers;
    private volatile boolean isShutdown = false;

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
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        if(runnable == null) {
            throw new NullPointerException("Runnable is null(");
        }
        try{
            queue.put(runnable);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Shuts down the pool, new tasks will not be accepted.
     */
    public void shutdown() {
        isShutdown = true;
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }

    /**
     * try to stop all actively executing task
     * @return List<Runnable>
     */
    public List<Runnable> shutdownNow() {
        isShutdown = true;
        List<Runnable> remainingTasks = new ArrayList<>();
        queue.drainTo(remainingTasks);
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
        return remainingTasks;
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
