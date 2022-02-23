package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Feeder;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase {

  private boolean end;

  public ElevatorCommand() {
    
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Elevator.setMotorSpeed(0.2);
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}