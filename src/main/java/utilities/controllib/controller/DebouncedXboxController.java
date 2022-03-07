package utilities.controllib.controller;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.GenericHID;

public class DebouncedXboxController extends GenericHID {

    private static final double DEADBAND = 0.1d;

    private enum Button {
        kLeftBumper(5),
        kRightBumper(6),
        kLeftStick(9),
        kRightStick(10),
        kA(1),
        kB(2),
        kX(3),
        kY(4),
        kBack(7),
        kStart(8);
    
        public final int value;
    
        Button(int value) {
          this.value = value;
        }
    }
    
    private enum Axis {
        kLeftX(0),
        kRightX(4),
        kLeftY(1),
        kRightY(5),
        kLeftTrigger(2),
        kRightTrigger(3);

        public final int value;
    
        Axis(int value) {
          this.value = value;
        }
    }

    private DebouncedButton kAButton = new DebouncedButton(this, Button.kA.value),
    kBButton = new DebouncedButton(this, Button.kB.value),
    kXButton = new DebouncedButton(this, Button.kX.value),
    kYButton = new DebouncedButton(this, Button.kY.value),
    kLeftBumperButton = new DebouncedButton(this, Button.kLeftBumper.value),
    kRightBumperButton = new DebouncedButton(this, Button.kRightBumper.value),
    kLeftStickButton = new DebouncedButton(this, Button.kLeftStick.value),
    kRightStickButton = new DebouncedButton(this, Button.kRightStick.value),
    kBackButton = new DebouncedButton(this, Button.kBack.value),
    kStartButton = new DebouncedButton(this, Button.kStart.value);

    private DebouncedAxis kLeftTriggerAxis = new DebouncedAxis(this, Axis.kLeftTrigger.value),
    kRightTriggerAxis = new DebouncedAxis(this, Axis.kRightTrigger.value);
    
    private double deadband;

    public DebouncedXboxController(int port) {
        this(port, DEADBAND);
    }

    public DebouncedXboxController(int port, double deadband) {
        super(port);
        this.deadband = deadband;
        HAL.report(tResourceType.kResourceType_XboxController, port + 1);
    }

    public DebouncedButton A(){
        return kAButton;
    }

    public DebouncedButton B(){
        return kBButton;
    }
    
    public DebouncedButton X(){
        return kXButton;
    }

    public DebouncedButton Y(){
        return kYButton;
    }

    public DebouncedButton Sart(){
        return kStartButton;
    }

    public DebouncedButton Back(){
        return kBackButton;
    }

    public DebouncedButton LeftBumper(){
        return kLeftBumperButton;
    }

    public DebouncedButton RightBumper(){
        return kRightBumperButton;
    }

    public DebouncedButton LeftStick(){
        return kLeftStickButton;
    }

    public DebouncedButton RightStick(){
        return kRightStickButton;
    }

    public DebouncedAxis LeftTrigger(){
        return kLeftTriggerAxis;
    }

    public DebouncedAxis RightTrigger(){
        return kRightTriggerAxis;
    }

    public double LeftX(){
        return modifyAxis(getRawAxis(Axis.kLeftX.value));
    }

    public double LeftY(){
        return modifyAxis(getRawAxis(Axis.kLeftY.value));
    }

    public double RightX(){
        return modifyAxis(getRawAxis(Axis.kRightX.value));
    }

    public double RightY(){
        return modifyAxis(getRawAxis(Axis.kRightY.value));
    }

    private double deadband(double value) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
          return 0.0;
        }
    }
    
    private double modifyAxis(double value) {
        value = deadband(value);
        value = Math.copySign(value * value, value);
        return value;
    }

}
