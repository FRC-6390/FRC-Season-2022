package frc.robot.subsystems.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DebouncedButton extends JoystickButton{
    private static float DEFUALT_DEBOUNCE_PERIOD = 0.5f;
    private Debouncer debouncer;

    public DebouncedButton(GenericHID joystick, int buttonNumber) {
        this(joystick, buttonNumber,DEFUALT_DEBOUNCE_PERIOD);
    }

    public DebouncedButton(GenericHID joystick, int buttonNumber, float debouncePeriod) {
        super(joystick, buttonNumber);
        debouncer = new Debouncer(this, debouncePeriod);
    }

    public boolean debounced() {
        return debouncer.get();
    }
    
}
