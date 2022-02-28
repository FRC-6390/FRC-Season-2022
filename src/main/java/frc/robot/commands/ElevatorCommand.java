package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase {

  private double velocity;

  public ElevatorCommand(double speed) {
    velocity = speed;
  }

  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
  }

  @Override
  public void execute() {
    Elevator.setMotorSpeed(velocity);
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