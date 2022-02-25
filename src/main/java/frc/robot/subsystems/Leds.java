package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Leds extends SubsystemBase {
  private static Spark Blinkin;
  double current;
  boolean override = false;

  public Leds() {
      Blinkin = new Spark(2);
  }

  public void setCustom(double value) {
      Blinkin.set(value);
  }

  public void setBlue() {
    override = true;
    Blinkin.set(-0.41);
    override = false;
  }

  public void setYellow() {
    override = true;
    Blinkin.set(0.69);    
    override = false;
  }

  public void setRed() {
    override = true;
    Blinkin.set(-0.57);
    override = false;
  }

  public void setGreen() {
    override = true;
    Blinkin.set(-0.37);
    override = false;
  }
  
  public void LimeLightTracking(){
    this.override = override;
    if(override == true){
        Blinkin.set(0.69);
    }
  }

  public void ShootNow (boolean override)
  {
    this.override = override;
    if(override == true){
        Blinkin.set(-0.05);
    }  
  }

  @Override
  public void periodic() {
    if(!override){
        Blinkin.set(current);
    }
  }
}