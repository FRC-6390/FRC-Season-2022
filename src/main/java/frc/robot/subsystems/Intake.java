package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.INTAKE;
import frc.robot.Constants.SHOOTER;;

public class Intake extends SubsystemBase {

  private CANSparkMax left, right;

  public Intake() {
    left = new CANSparkMax(INTAKE.LEFT, MotorType.kBrushless);
    right = new CANSparkMax(INTAKE.RIGHT, MotorType.kBrushless);
  }

  public void set(double speed){
    left.set(speed);
    right.set(-speed);
  }

  @Override
  public void periodic() {

  }
}
