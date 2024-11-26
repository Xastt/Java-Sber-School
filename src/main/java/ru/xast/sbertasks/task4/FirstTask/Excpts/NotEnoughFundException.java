package ru.xast.sbertasks.task4.FirstTask.Excpts;

/**
 * Exception, which throws in case if user wants to
 * withdraw money, but the amount != money
 * Returns message.
 * @author Khasrovyan Artyom
 */
public class NotEnoughFundException extends Exception{
    public NotEnoughFundException(String message){
        super(message);
    }
}
