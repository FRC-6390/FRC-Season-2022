package utilities.controllib.controller;

import edu.wpi.first.wpilibj.GenericHID;

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
}
