package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

/**
 * interface, realizing all functions of real terminal
 * @author Khasrovyan Artyom
 * @see TerminalImpl
 */
public interface Terminal {
    double checkBalance() throws AccountIsLockedException;
    void withdraw(int amount) throws InvalidAmountException, AccountIsLockedException, NotEnoughFundException;
    void deposit(int amount) throws AccountIsLockedException, InvalidAmountException;
    void enterPin(String pin) throws InvalidPinException, AccountIsLockedException;
}
