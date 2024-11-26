package ru.xast.sbertasks.task4.FirstTask.Excpts;

/**
 * Exception, which throws in case if user try to withdraw or deposit
 * his money but amount / 100 != 0
 * Returns message.
 * @author Khasrovyan Artyom
 */
public class InvalidAmountException extends Exception{
    public InvalidAmountException(String message){
        super(message);
    }
}
