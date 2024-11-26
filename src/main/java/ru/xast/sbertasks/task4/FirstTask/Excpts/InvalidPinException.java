package ru.xast.sbertasks.task4.FirstTask.Excpts;

/**
 * Exception, which throws in case if user enter not number
 * Returns message.
 * @author Khasrovyan Artyom
 */
public class InvalidPinException extends Exception{
    public InvalidPinException(String message){
        super(message);
    }
}
