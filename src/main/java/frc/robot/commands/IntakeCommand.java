package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {

  private boolean reverse = false;

  public IntakeCommand(boolean reverse) {
    this.reverse = reverse;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    IntakeSubsystem.setMotorSpeed(reverse ? -Constants.INTAKE.INTAKE_VELOCITY : Constants.INTAKE.INTAKE_VELOCITY);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
