package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Leds extends SubsystemBase {
  private static Servo Blinkin;
  static double current;
  static boolean isOverride = false;

  public static enum LED_COLOURS {
      
    Blue(-0.41),
    Yellow(0.69),
    Red(-0.57),
    Green(-0.37);

    private double value;
    private LED_COLOURS(double value){
      this.value = value;
    }

    public double value(){
      return value;
    }
  }

  static {
    Blinkin = new Servo(2);
  }

  public static void setCustom(double value) {
    Blinkin.set(value);
    current = value;
  }

  public static void set(LED_COLOURS colour) {
    Blinkin.set(colour.value());
    current = colour.value();
  }

  // public static void setBlue() {
  //   isOverride = true;
  //   Blinkin.set(-0.41);
  //   isOverride = false;
  // }

  // public static void setYellow() {
  //   isOverride = true;
  //   Blinkin.set(0.69);    
  //   isOverride = false;
  // }

  // public static void setRed() {
  //   isOverride = true;
  //   Blinkin.set(-0.57);
  //   isOverride = false;
  // }

  // public static void setGreen() {
  //   isOverride = true;
  //   Blinkin.set(-0.37);
  //   isOverride = false;
  // }
  
  // public static void LimeLightTracking(){
  //   if(isOverride == true){
  //       Blinkin.set(0.69);
  //   }
  // }

  // public static void ShootNow(boolean override)
  // {
  //   isOverride = override;
  //   if(override == true){
  //       Blinkin.set(-0.05);
  //   }  
  // }

  @Override
  public void periodic() {
    Blinkin.set(current);
  }
}