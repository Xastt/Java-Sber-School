package ru.xast.sbertasks.task4.FirstTask;

/**
 * Class, which check: entered pin equals user pin or not
 * @author Khasrovyan Artyom
 * @see TerminalImpl
 */
public class PinValidator {

    private final String pin;

    public PinValidator(String pin) {
        this.pin = pin;
    }

    /**
     * Method which check: entered pin equals user pin or not
     * @param InpPin
     * @return true/false
     */
    public boolean validate(String InpPin) {
        return pin.equals(InpPin);
    }
}
