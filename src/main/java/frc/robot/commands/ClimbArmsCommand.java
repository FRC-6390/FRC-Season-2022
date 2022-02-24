package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Feeder;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbArmsCommand extends CommandBase {

  private double angle;

  public ClimbArmsCommand(double servoAngle) {
    angle = servoAngle;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    ClimbArms.setAngle(angle);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}