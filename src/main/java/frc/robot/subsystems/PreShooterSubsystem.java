package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PreShooterSubsystem extends SubsystemBase {
    private static CANSparkMax preShooter;

    static {
        preShooter = new CANSparkMax(Constants.MOTOR.PRE_SHOOTER, MotorType.kBrushless);
    }

    public PreShooterSubsystem(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        preShooter.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        preShooter.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}
