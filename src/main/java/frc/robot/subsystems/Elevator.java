package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private static CANSparkMax left, right;

    static {
        left = new CANSparkMax(Constants.ELEVATOR.LEFT, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ELEVATOR.RIGHT, MotorType.kBrushless);
    }

    public Elevator(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        left.set(velocity);
        right.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        left.setIdleMode(mode);
        right.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}