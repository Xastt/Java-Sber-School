package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.AccountIsLockedException;
import ru.xast.sbertasks.task4.FirstTask.Excpts.InvalidAmountException;
import ru.xast.sbertasks.task4.FirstTask.Excpts.InvalidPinException;
import ru.xast.sbertasks.task4.FirstTask.Excpts.NotEnoughFundException;

import java.util.Scanner;

public class TerminalRun {

    Scanner scanner = new Scanner(System.in);
    private final TerminalImpl terminalImpl;


    public TerminalRun(TerminalImpl terminalImpl) {
        this.terminalImpl = terminalImpl;
    }

    public void run() {
        while (true) {
            System.out.println("For exit write: 'exit'" + "\nEnter your PIN: ");
            String inputPin = scanner.nextLine();
            if (inputPin.equals("exit")) {
                System.out.println("Bye Bye!");
                break;
            }
            try{
                terminalImpl.enterPin(inputPin);
                System.out.println("PIN entered successfully)");
                while (true){
                    System.out.println("Enter action:" +
                    "\n1 - Check balance" +
                    "\n2 -  Deposit money"+
                    "\n3 - Withdraw money" +
                    "\n4 - Exit");
                    String action = scanner.nextLine();

                    switch (action) {
                        case "1":
                            double balance = terminalImpl.checkBalance();
                            System.out.println("Your balance: " + balance);
                            break;
                        case "2":
                            System.out.println("Enter money to deposit");
                            int deposit = scanner.nextInt();
                            terminalImpl.deposit(deposit);
                            System.out.println("Deposited money: " + deposit);
                            break;
                        case "3":
                            System.out.println("Enter money to withdraw: ");
                            int withdrawMoney = scanner.nextInt();
                            terminalImpl.withdraw(withdrawMoney);
                            System.out.println("Withdraw money: " + withdrawMoney);
                            break;
                        case "4":
                            System.out.println("Bye Bye!");
                            break;
                        default:
                            System.out.println("Invalid action. Try again");
                    }
                }
            }catch(AccountIsLockedException e){
                System.out.println(e.getMessage());
            }catch (InvalidPinException e){
                System.out.println(e.getMessage());
            }catch (InvalidAmountException e){
                System.out.println(e.getMessage());
            }catch (NotEnoughFundException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
