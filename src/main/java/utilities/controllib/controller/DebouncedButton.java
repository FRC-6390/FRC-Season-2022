package utilities.controllib.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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

    public DebouncedButton whenDebounced(Command command){
        return whenDebounced(command, true);
    }

    public DebouncedButton whenDebounced(Runnable runnable){
        return whenDebounced(new InstantCommand(runnable), true);
    }
    
    public DebouncedButton whenDebounced(Runnable runnable, boolean interruptible){
        return whenDebounced(new InstantCommand(runnable), interruptible);
    }

    public DebouncedButton whenDebounced(Command command, boolean interruptible){
        CommandScheduler.getInstance()
            .addButton(
                new Runnable() {
                  @Override
                  public void run() {
                      if(debounced())command.schedule(interruptible);
                    }
                });
        return this;
    }
}