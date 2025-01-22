package ru.xast.sbertasks.task11;

import java.util.List;

/**
 * interface which contains basic operations
 * with thread's
 * @author Khasrovyan Artyom
 */
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

    /**
     * shutdown the pool, new tasks will not be accepted.
     */
    void shutdown();

    /**
     * try to stop all actively executing task
     * @return List
     */
    List<Runnable> shutdownNow();
}
