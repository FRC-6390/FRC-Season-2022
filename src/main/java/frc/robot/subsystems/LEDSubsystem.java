package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;

public class LEDSubsystem extends SubsystemBase {

  static Spark kBlinkin; 
  static double kValue;
  
  public static enum LEDColour {
    Green(-0.37),
    Blue(-0.41),
    Yellow(0.69),
    Red(-0.57);

    double value;
    private LEDColour(double val){
        value = val;
    }
  }

  static {
    kBlinkin = new Spark(ROBOT.BLINKIN);
  }

  public static void setValue(LEDColour colour){
    setValue(colour.value);
  }

  public static void setValue(double value){
    kValue = value;
  }

  @Override
  public void periodic() {
    kBlinkin.set(kValue);
  }
}
