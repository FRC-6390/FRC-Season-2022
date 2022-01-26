package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederCommand extends CommandBase {

  private boolean reverse = false;

  public FeederCommand(boolean reverse) {
    this.reverse = reverse;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    IntakeSubsystem.setMotorSpeed(reverse ? -Constants.ROBOT.FEEDER_SPEED : Constants.ROBOT.FEEDER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
