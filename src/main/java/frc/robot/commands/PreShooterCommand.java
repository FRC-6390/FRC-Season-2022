package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.PreShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PreShooterCommand extends CommandBase {

  private boolean reverse = false;

  public PreShooterCommand(boolean reverse) {
    this.reverse = reverse;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    PreShooterSubsystem.setMotorSpeed(reverse ? -Constants.SHOOTER.HIGH_VELOCITY : Constants.SHOOTER.HIGH_VELOCITY);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
