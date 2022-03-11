package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;
import utilities.sensorlib.Blinkin;
import utilities.sensorlib.Blinkin.BlinkinColour;

public class LEDSubsystem extends SubsystemBase {

  static Blinkin kBlinkin; 
  static double kValue;

  static {
    kBlinkin = new Blinkin(ROBOT.BLINKIN);
  }

  public static void setValue(BlinkinColour colour){
    setValue(colour.value);
  }

  public static void setValue(double value){
    kValue = value;
  }

  @Override
  public void periodic() {
    kBlinkin.set(kValue);
  }
  
  @Override
  public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("LED");
      builder.addDoubleProperty("Colour Value", ()->kValue, (val)->kValue = val);
  }
}
