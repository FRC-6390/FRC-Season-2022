package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeederCommand extends CommandBase {

  private boolean end;

  public FeederCommand() {
    
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Feeder.setMotorSpeed(0.8);
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