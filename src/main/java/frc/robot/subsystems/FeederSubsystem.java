package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FEEDER;

public class FeederSubsystem extends SubsystemBase {

  static CANSparkMax kFeederMotor; 
  static ColorSensorV3 kColourSensor;

  static {
    kFeederMotor = new CANSparkMax(FEEDER.FEEDER_MOTOR, MotorType.kBrushless);
    //kColourSensor = new ColorSensorV3(port);
  }

  public static void setFeederSpeed(double speed){
    kFeederMotor.set(speed);
  }

  public static Color getBallColour(){
    return kColourSensor.getColor();
  }

  public static void stop(){
    kFeederMotor.stopMotor();
  }

  @Override
  public void periodic() {}
}
