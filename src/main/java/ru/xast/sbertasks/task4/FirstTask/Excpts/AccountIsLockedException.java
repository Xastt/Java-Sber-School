package ru.xast.sbertasks.task4.FirstTask.Excpts;

/**
 * Exception, which throws in case if user enter wrong pin more than 3 times
 * Returns message and time to unlock the access.
 * @author Khasrovyan Artyom
 */
public class AccountIsLockedException extends Exception{

    private final long timeToOpen;

    public AccountIsLockedException(String message, long timeToOpen){
        super(message);
        this.timeToOpen = timeToOpen;
    }

    public long getTimeToOpen(){
        return timeToOpen;
    }

    /**
     * @return Exception message and time to unlock the access
     */
    @Override
    public String getMessage(){
        return super.getMessage() + "\nRemaining time: " + timeToOpen + " seconds.";
    }
}
