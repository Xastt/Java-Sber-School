package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private String enteredPin = "";
    private int failedAttempts = 0;
    private boolean isLocked = false;
    private LocalDateTime lockTime;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public double checkBalance() throws AccountIsLockedException {
        checkLocked();
        System.out.println("Balance: ");
        return server.checkBalance(enteredPin);
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException, AccountIsLockedException, NotEnoughFundException {
        checkLocked();
        if(amount % 100 != 0 || amount < 0){
            throw new InvalidAmountException("Amount must be positive and divisible by 100");
        }
        server.withdraw(enteredPin, amount);
    }

    @Override
    public void deposit(int amount) throws AccountIsLockedException, InvalidAmountException {
        checkLocked();
        if(amount % 100 != 0 || amount < 0){
            throw new InvalidAmountException("Amount must be positive and divisible by 100");
        }
        server.deposit(enteredPin, amount);
    }

    @Override
    public void enterPin(String pin) throws InvalidPinException, AccountIsLockedException {
        checkLocked();
        if(pin.length()!=4 && !pin.matches("[0-9]{4}")){
            failedAttempts++;
            if(failedAttempts>3){
                lockTime = LocalDateTime.now();
                throw new AccountIsLockedException("Account is locked. Try again in ", 10000);
            }
            throw new InvalidPinException("Pin is not valid");
        }
        if(pinValidator.validate(pin)){
            enteredPin = pin;
            failedAttempts = 0;
        }else{
            failedAttempts++;
            throw new InvalidPinException("Pin is not valid");
        }
    }

    private void checkLocked() throws AccountIsLockedException {
        if(lockTime != null){
            long secondsLocked = Duration.between(lockTime, LocalDateTime.now()).getSeconds();
            if(secondsLocked < 10){
                throw new AccountIsLockedException("Account is locked. Try again in ", secondsLocked);
            }else{
                lockTime = null;
            }
        }
    }
}
