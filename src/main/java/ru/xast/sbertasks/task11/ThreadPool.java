package ru.xast.sbertasks.task11;

public interface ThreadPool {
    /**
     * Starts thread's
     */
    void start();

    /**
     * Puts task's into the queue.
     * The thread must execute this task.
     * @param runnable
     */
    void execute(Runnable runnable);
}
