package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

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
        server.checkBalance(enteredPin);
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException, AccountIsLockedException, NotEnoughFundException {
        checkLocked();
        if(amount % 100 != 0 || amount <= 0){
            throw new InvalidAmountException("Amount must be positive and divisible by 100");
        }
        server.withdraw(enteredPin, amount);
    }

    @Override
    public void deposit(int amount) throws AccountIsLockedException, InvalidAmountException {
        checkLocked();
        if(amount % 100 != 0 || amount <= 0){
            throw new InvalidAmountException("Amount must be positive and divisible by 100");
        }
        server.deposit(enteredPin, amount);
    }


    public void enterPin() throws AccountIsLockedException, InvalidPinException {
        checkLocked();
        Scanner scanner = new Scanner(System.in);
        while (pinCode.length() < 4) {
            System.out.print("Enter the next digit of the PIN code: ");
            String input = scanner.nextLine();

            try{
                if (input.length() != 1 || !input.matches("[0-9]")) {
                    throw new InvalidPinException("Please only enter positive digits.");
                }
            }catch (InvalidPinException e){
                System.out.println(e.getMessage());
                continue;
            }

            pinCode.append(input);

            if (pinCode.length() == 4) {
                validatePin();
            }
        }
    }

    private void validatePin() throws AccountIsLockedException, InvalidPinException {
        if (pinValidator.validate(String.valueOf(pinCode))) {
            System.out.println("Access granted.");
            failedAttempts = 0;
            pinCode.setLength(0);
        } else {

            try{
                failedAttempts++;
                throw new InvalidPinException("Incorrect PIN");
            }catch (InvalidPinException e){
                System.out.println(e.getMessage());
            }

            if (failedAttempts > 3) {
                try{
                    lockTime = LocalDateTime.now().plusSeconds(10);
                    throw new AccountIsLockedException("Account is locked.", 10);
                }catch (AccountIsLockedException e){
                    System.out.println(e.getMessage());
                }

            } else {
                pinCode.setLength(0);
                System.out.println("Try again.");
            }
        }
    }

    private void checkLocked() throws AccountIsLockedException {
        if (lockTime != null && LocalDateTime.now().isBefore(lockTime)) {
            long secondsUntilUnlock = Duration.between(LocalDateTime.now(), lockTime).getSeconds();
            throw new AccountIsLockedException("Account is locked.", secondsUntilUnlock);
        }
    }

    public static void main (String[] args) throws InvalidPinException, AccountIsLockedException {
        TerminalServer terminalServer = new TerminalServer(2000);
        PinValidator pinValidator1 = new PinValidator("1234");
        TerminalImpl terminal = new TerminalImpl(terminalServer, pinValidator1);
        terminal.enterPin();
    }
}
