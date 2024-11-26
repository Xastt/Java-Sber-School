package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.InvalidAmountException;
import ru.xast.sbertasks.task4.FirstTask.Excpts.NotEnoughFundException;

/**
 * Class, which is used for operation with terminal
 * @see Terminal
 * @see TerminalImpl
 * @author Khasrovyan Artyom
 */
public class TerminalServer {
    private double balance;

    public TerminalServer(double balance) {
        this.balance = balance;
    }

    /**
     * Method for printing out the balance
     * @param pin
     */
    public double checkBalance(String pin){
        return balance;
    }

    /**
     * Method, which deposit money on bank account. And check that amount divisible by 100
     * @param pin
     * @param amount
     * @throws InvalidAmountException
     */
    public void deposit(String pin, int amount) throws InvalidAmountException {
        if (amount / 100 != 0){
            throw new InvalidAmountException("Invalid amount for deposit. Amount must be divided by 100!");
        }else{
            balance += amount;
            System.out.println("Deposited: " + amount + "\nNew balance: " + balance);
        }
    }

    /**
     * Method, which deposit money on bank account. And check that amount divisible by 100
     * and amount for withdraw more than balance
     * @param pin
     * @param amount
     * @throws InvalidAmountException
     * @throws NotEnoughFundException
     */
    public void withdraw(String pin, int amount) throws InvalidAmountException, NotEnoughFundException {
        if(amount > balance){
            throw new NotEnoughFundException("Not enough fund. Available balance: " + balance);
        } else if (amount / 100 != 0) {
            throw new InvalidAmountException("Invalid amount for withdraw. Amount must be divided by 100!");
        }else{
            balance -= amount;
        }
    }
}
