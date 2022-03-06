package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ReleaseArms extends CommandBase {

  private double velocity;
  private boolean done = false;
  private double seconds = 2.3 * 1000;
  private double timeout;

  public ReleaseArms(double speed) {
    velocity = speed;
  }


  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    Elevator.resetEncoder();
    timeout = System.currentTimeMillis() + seconds;
    TurretedShooter.seeking = false;
  }

  @Override
  public void execute() {

    if(RobotContainer.start.debounced()) cancel();
    //center the turret to release the arms
    TurretedShooter.turret.set(0.1);

    if(timeout < System.currentTimeMillis()){
      TurretedShooter.turret.set(0.0);
      ClimbArms.close();
      Timer.delay(1.5);
      done = true;
    }
    
    
  }

  @Override
  public void end(boolean interrupted) {
    TurretedShooter.turret.set(0.0);
    // TurretedShooter.seeking = true;
    TurretedShooter.turret.set(0.0);
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}



