package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private static CANSparkMax shooterLeft, shooterRight;
    public static CANCoder leftEncoder, rightEncoder;

    static {
        shooterLeft = new CANSparkMax(Constants.SHOOTER.LEFT, MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.SHOOTER.RIGHT, MotorType.kBrushless);
        leftEncoder = new CANCoder(Constants.SHOOTER.LEFT_ENCODER);
        rightEncoder = new CANCoder(Constants.SHOOTER.RIGHT_ENCODER);
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

    public static double getVolocity(){
      double left = leftEncoder.getVelocity();
      double right = rightEncoder.getVelocity();
      double volocity = left + right / 2;
      return volocity;
    }

    public static double getEncoderPositions(){
      double position = (leftEncoder.getAbsolutePosition() + rightEncoder.getAbsolutePosition()) / 2;
      return position;
    }

    @Override
    public void periodic() {
  
    }
}
