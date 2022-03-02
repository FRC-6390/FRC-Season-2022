package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    public static CANSparkMax left, right;
    private static DigitalInput topSwitch, bottomSwitch;
    private static CANCoder encoder;
    private static ShuffleboardTab tab = Shuffleboard.getTab("Climb");

    
    static {
        left = new CANSparkMax(Constants.ELEVATOR.LEFT, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ELEVATOR.RIGHT, MotorType.kBrushless);
        topSwitch = new DigitalInput(Constants.ELEVATOR.TOP_LIMIT_SWITCH);
        bottomSwitch = new DigitalInput(Constants.ELEVATOR.BOTTOM_LIMIT_SWITCH);

        encoder = new CANCoder(Constants.ELEVATOR.ENCODER, "Swerve CANivore");
        //setMotorsIdleMode(IdleMode.kBrake);

        //Shuffleboard outputs
        tab.getLayout("Climb", BuiltInLayouts.kList).addBoolean("Elevator Switch", () -> getBottomSwitch());
        tab.getLayout("Climb", BuiltInLayouts.kList).addNumber("Elevator Encoder", () -> getEncoder());
    }
    
    public static void setMotorSpeed(double velocity){
        left.set(velocity);
        right.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        left.setIdleMode(mode);
        right.setIdleMode(mode);
    }

    public static boolean getTopSwitch(){
        return topSwitch.get();
    }
    
    public static boolean getBottomSwitch(){
        return bottomSwitch.get();
    }

    public static double  getEncoder(){
        return encoder.getPosition();
    }

    public static ErrorCode resetEncoder(){
        return encoder.setPosition(0);
    }

    @Override
    public void periodic() {
    }
}