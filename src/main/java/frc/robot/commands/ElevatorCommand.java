package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

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
    TurretedShooter.seeking = false;
  }

  @Override
  public void execute() {
    if(TurretedShooter.getHomePosition() == true){
      TurretedShooter.turret.set(-0.1);
    }

    if(TurretedShooter.getHomePosition() == false){
      TurretedShooter.turret.set(0.0);
      Elevator.setMotorSpeed(velocity);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
    TurretedShooter.turret.set(0.0);
    // TurretedShooter.seeking = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}