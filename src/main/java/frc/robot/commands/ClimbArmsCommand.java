package frc.robot.commands;

import frc.robot.subsystems.ClimbArms;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbArmsCommand extends CommandBase {

  public ClimbArmsCommand() {

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //releases the servos allowing the arms to extend
    ClimbArms.open();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}