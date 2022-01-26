package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederSubsystem extends SubsystemBase {
    private static CANSparkMax feeder;

    static {
        feeder = new CANSparkMax(Constants.MOTOR.FEEDER, MotorType.kBrushless);
    }

    public FeederSubsystem(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        feeder.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        feeder.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}
