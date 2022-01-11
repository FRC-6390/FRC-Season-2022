package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private static CANSparkMax shooterLeft, shooterRight;

    static {
        shooterLeft = new CANSparkMax(Constants.MOTOR.SHOOTER_LEFT, MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.MOTOR.SHOOTER_RIGHT, MotorType.kBrushless);
    }

    public ShooterSubsystem(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        shooterLeft.set(velocity);
        shooterRight.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        shooterLeft.setIdleMode(mode);
        shooterRight.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}
