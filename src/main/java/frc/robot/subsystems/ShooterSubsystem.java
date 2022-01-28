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
        shooterLeft = new CANSparkMax(Constants.MOTOR.SHOOTER_LEFT, MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.MOTOR.SHOOTER_RIGHT, MotorType.kBrushless);
        leftEncoder = new CANCoder(Constants.SENSOR.LEFT_SHOOTER_ENCODER.getEncoderId());
        rightEncoder = new CANCoder(Constants.SENSOR.RIGHT_SHOOTER_ENCODER.getEncoderId());
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
