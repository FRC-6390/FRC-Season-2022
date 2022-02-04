package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederCommand extends CommandBase {

  private boolean reverse = false;

  public FeederCommand(boolean reverse) {
    this.reverse = reverse;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    FeederSubsystem.setMotorSpeed(reverse ? -Constants.FEEDER.FEEDER_VELOCITY : Constants.FEEDER.FEEDER_VELOCITY);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
