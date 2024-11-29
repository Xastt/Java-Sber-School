package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.util.Scanner;

/**
 * Class, for interaction between user and terminal
 * @see TerminalServer
 * @see PinValidator
 * @see TerminalImpl
 * @author Khasrovyan Artyom
 */
public class TerminalConsoleApp {
    private final TerminalImpl terminal;
    private final Scanner scanner;

    public TerminalConsoleApp(TerminalImpl terminal) {
        this.terminal = terminal;
        this.scanner = new Scanner(System.in);
    }

    public void start() throws InvalidPinException, AccountIsLockedException {
        System.out.println("FIRST, enter your PIN!");

        terminal.enterPin();

        if(terminal.getAccess()){
            while (true) {
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Deposit money");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");
                int choice = scanner.nextInt();
                try {
                    switch (choice) {
                        case 1:
                            terminal.checkBalance();
                            break;
                        case 2:
                            withdraw();
                            break;
                        case 3:
                            deposit();
                            break;
                        case 4:
                            System.out.println("Exit.");
                            return;
                        default:
                            System.out.println("Incorrect choice. Please try again.");
                        }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter money for withdraw: ");
        int amount = scanner.nextInt();
        try {
            terminal.withdraw(amount);
        } catch (InvalidAmountException | AccountIsLockedException | NotEnoughFundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit() {
        System.out.print("Enter money for deposit: ");
        int amount = scanner.nextInt();
        try {
            terminal.deposit(amount);
        } catch (AccountIsLockedException | InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InvalidPinException, AccountIsLockedException {
        TerminalServer server = new TerminalServer(2000);
        PinValidator pinValidator = new PinValidator("1234");
        TerminalImpl terminal = new TerminalImpl(server, pinValidator);
        TerminalConsoleApp app = new TerminalConsoleApp(terminal);
        app.start();
    }
}
