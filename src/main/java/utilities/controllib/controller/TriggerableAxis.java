package utilities.controllib.controller;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

public class TriggerableAxis extends Button{

    private static final double THRESHOLD = 0.5;

    public TriggerableAxis(GenericHID genericHID, int axis){
        this(genericHID, axis, THRESHOLD);
    }

    public TriggerableAxis(GenericHID genericHID, int axis, double threshold){
        super(() -> genericHID.getRawAxis(axis) > threshold);
    }
}
