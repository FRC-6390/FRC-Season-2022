package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederCommand extends CommandBase {

  private double intakeVelocity, feederVelocity;

  //test commit for github one more time
  public FeederCommand(double intakeSpeed, double feederSpeed) {
    intakeVelocity = intakeSpeed;
    feederVelocity = feederSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Intake.setMotorSpeed(intakeVelocity);
    Feeder.setMotorSpeed(feederVelocity);
  }

  @Override
  public void end(boolean interrupted) {
    Feeder.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}