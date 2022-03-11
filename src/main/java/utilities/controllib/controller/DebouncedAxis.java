package utilities.controllib.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class DebouncedAxis extends TriggerableAxis{

    private Debouncer debouncer;

    public DebouncedAxis(GenericHID joystick, int axis) {
        super(joystick, axis);
        debouncer = new Debouncer();
    }

    public DebouncedAxis(GenericHID joystick, int axis, float debouncePeriod) {
        super(joystick, axis);
        debouncer = new Debouncer(debouncePeriod);
    }

    public boolean debounced() {
        return get() ? debouncer.get() : false;
    }

    public DebouncedAxis whenDebounced(Command command){
        return whenDebounced(command, true);
    }

    public DebouncedAxis whenDebounced(Runnable runnable, boolean interruptible){
        return whenDebounced(new InstantCommand(runnable));
    }

    public DebouncedAxis whenDebounced(Command command, boolean interruptible){
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
