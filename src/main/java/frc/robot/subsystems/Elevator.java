package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private static CANSparkMax left, right;
    private static DigitalInput topSwitch, bottomSwitch;

    static{
        left = new CANSparkMax(Constants.ELEVATOR.LEFT, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ELEVATOR.RIGHT, MotorType.kBrushless);
        topSwitch = new DigitalInput(Constants.ELEVATOR.TOP_LIMIT_SWITCH);
        bottomSwitch = new DigitalInput(Constants.ELEVATOR.BOTTOM_LIMIT_SWITCH);
        setMotorsIdleMode(IdleMode.kBrake);
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

    @Override
    public void periodic() {
    }
}