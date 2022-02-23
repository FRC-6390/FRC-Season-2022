package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Feeder;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase {

  private boolean end;
  private double velocity;

  public ElevatorCommand(double speed) {
    velocity = speed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
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