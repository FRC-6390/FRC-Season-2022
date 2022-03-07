package utilities.controllib.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DebouncedButton extends JoystickButton {

    private Debouncer debouncer;

    public DebouncedButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
        debouncer = new Debouncer();
    }

    public DebouncedButton(GenericHID joystick, int buttonNumber, float debouncePeriod) {
        super(joystick, buttonNumber);
        debouncer = new Debouncer(debouncePeriod);
    }

    public boolean debounced() {
        return get() ? debouncer.get() : false;
    }
}