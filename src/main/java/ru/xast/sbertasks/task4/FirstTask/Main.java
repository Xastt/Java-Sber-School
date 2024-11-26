package ru.xast.sbertasks.task4.FirstTask;

public class Main {
    public static void main(String[] args) {
        TerminalServer server = new TerminalServer(1000);
        PinValidator pinValidator = new PinValidator("1234");
        TerminalImpl terminal = new TerminalImpl(server, pinValidator);
        TerminalRun app = new TerminalRun(terminal);
        app.run();
    }


}
