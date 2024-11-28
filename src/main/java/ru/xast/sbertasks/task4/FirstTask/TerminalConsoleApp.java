package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.util.Scanner;

public class TerminalConsoleApp {
    private final TerminalImpl terminal;
    private final Scanner scanner;

    public TerminalConsoleApp(TerminalImpl terminal) {
        this.terminal = terminal;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            //System.out.println("1. Ввести PIN-код");
            System.out.println("2. Check Balance");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Deposit money");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            try {
                switch (choice) {
                    case 1:
                        enterPin();
                        break;
                    case 2:
                        checkBalance();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        deposit();
                        break;
                    case 5:
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

    private void enterPin() {
        System.out.print("Введите ваш PIN-код: ");
        String pin = scanner.nextLine();
        try {
            terminal.enterPin(pin);
            System.out.println("PIN-код успешно введен.");
        } catch (InvalidPinException | AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkBalance() {
        try {
            double balance = terminal.checkBalance();
            System.out.println("Ваш баланс: " + balance);
        } catch (AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withdraw() {
        System.out.print("Введите сумму для снятия: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        try {
            terminal.withdraw(amount);
            System.out.println("Сумма " + amount + " успешно снята.");
        } catch (InvalidAmountException | AccountIsLockedException | NotEnoughFundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit() {
        System.out.print("Введите сумму для пополнения: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        try {
            terminal.deposit(amount);
            System.out.println("Сумма " + amount + " успешно пополнена.");
        } catch (AccountIsLockedException | InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        TerminalServer server = new TerminalServer(2000);
        PinValidator pinValidator = new PinValidator("1234");

        TerminalImpl terminal = new TerminalImpl(server, pinValidator);
        TerminalConsoleApp app = new TerminalConsoleApp(terminal);

        app.start();
    }
}
