package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.time.Duration;
import java.time.LocalDateTime;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;
    private String enteredPin = "";
    private int failedAttempts = 0;
    private LocalDateTime lockTime;

    StringBuilder pinCode = new StringBuilder();

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void checkBalance() throws AccountIsLockedException {
        checkLocked();
        System.out.println("Balance: ");
        server.checkBalance(enteredPin);
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
        int count = 0;

        checkLocked();

        System.out.println("Write your pin here, use 'enter' after each number");

        try {
            for (int i = 0; i <= count; i++) {
                if (pin.matches("[0-9]")) {
                    pinCode.append(pin);
                    count += 1;
                } else {
                    throw new InvalidPinException("Pin must be a number");
                }
            }
        }catch (InvalidPinException e){
            System.out.println(e.getMessage());
        }

        try{
            if(pinValidator.validate(String.valueOf(pinCode))){
                enteredPin = pinCode.toString();
                failedAttempts = 0;
            }else{
                failedAttempts++;
                if(failedAttempts > 3){
                    lockTime = LocalDateTime.now();
                    throw new AccountIsLockedException("Account is locked.", 10000);

                }
            }
        }catch (AccountIsLockedException e){
            System.out.println(e.getMessage());
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
