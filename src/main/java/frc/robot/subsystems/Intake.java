package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.INTAKE;

public class Intake extends SubsystemBase {

  private static PWMSparkMax left;
  private static PWMSparkMax right;

  static {
    left = new PWMSparkMax(INTAKE.LEFT);
    right = new PWMSparkMax(INTAKE.RIGHT);
    left.setInverted(true);
  }

  public static void setMotorSpeed(double speed){
    left.set(speed);
    right.set(speed);
  }

  @Override
  public void periodic() {

  }
}
