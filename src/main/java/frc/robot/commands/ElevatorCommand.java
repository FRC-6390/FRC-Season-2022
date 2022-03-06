package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase {

  private double velocity;
  private boolean override;

  public ElevatorCommand(double speed, boolean override) {
    velocity = speed;
    this.override = override;
  }

  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    TurretedShooter.seeking = false;
  }

  @Override
  public void execute() {
    if(!override&&TurretedShooter.getHomePosition() == true){
      TurretedShooter.turret.set(-0.1);
    }

    if(override||TurretedShooter.getHomePosition() == false){
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