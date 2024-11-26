package ru.xast.sbertasks.task4.FirstTask;

import ru.xast.sbertasks.task4.FirstTask.Excpts.AccountIsLockedException;
import ru.xast.sbertasks.task4.FirstTask.Excpts.InvalidPinException;

public class Main {
    public static void main(String[] args) {
        TerminalServer terminalServer = new TerminalServer(1000);
        PinValidator pinValidator = new PinValidator("1234");
        Terminal terminal = new TerminalImpl(terminalServer, pinValidator);



    }
}
