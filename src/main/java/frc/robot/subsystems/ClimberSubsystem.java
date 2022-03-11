package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ELEVATOR;
import utilities.LimitSwitch;

public class ClimberSubsystem extends SubsystemBase {

  static CANSparkMax kLeftMotor, kRightMotor; 
  static CANCoder kElevatorEncoder;
  static LimitSwitch kBottomLimit;


  static {
    kLeftMotor = new CANSparkMax(ELEVATOR.LEFT, MotorType.kBrushless);
    kRightMotor = new CANSparkMax(ELEVATOR.RIGHT, MotorType.kBrushless);
    kElevatorEncoder = new CANCoder(ELEVATOR.ENCODER);
    kBottomLimit = new LimitSwitch(ELEVATOR.LIMIT_SWITCH);
    kLeftMotor.setIdleMode(IdleMode.kBrake);
    kRightMotor.setIdleMode(IdleMode.kBrake);
  }

  public static void setElevatorSpeed(double speed){
    if(!kBottomLimit.get()) speed = 0;
    kRightMotor.set(speed);
    kLeftMotor.set(speed);
  }

  public static void stop(){
    kRightMotor.stopMotor();
    kLeftMotor.stopMotor();
  }

  @Override
  public void periodic() {}

  @Override
  public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("Climber");
      builder.addDoubleProperty("Left Motor Speed", kLeftMotor::get, null);
      builder.addDoubleProperty("Right Motor Speed", kRightMotor::get, null);
      builder.addBooleanProperty("Limit Switch", kBottomLimit::get, null);
  }
}
