package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.*;

import java.util.Scanner;

//TODO если пользователь ввел пин в самом начале, ставим флаг true, по умолчанию false
//TODO во всех методах проверяем флаг если true то даем доступ иначе кидаем в метод ввода пина
public class TerminalConsoleApp {
    private final TerminalImpl terminal;
    private final Scanner scanner;

    public TerminalConsoleApp(TerminalImpl terminal) {
        this.terminal = terminal;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1.Enter PIN");
            System.out.println("2. Check Balance");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Deposit money");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();


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
        try {
            terminal.enterPin();
        } catch (InvalidPinException | AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkBalance() {
        try {
            terminal.checkBalance();
        } catch (AccountIsLockedException e) {
            System.out.println(e.getMessage());
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

    public static void main(String[] args) {
        TerminalServer server = new TerminalServer(2000);
        PinValidator pinValidator = new PinValidator("1234");
        TerminalImpl terminal = new TerminalImpl(server, pinValidator);
        TerminalConsoleApp app = new TerminalConsoleApp(terminal);
        app.start();
    }
}
