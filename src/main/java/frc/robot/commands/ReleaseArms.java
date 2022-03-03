package frc.robot.commands;

import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ReleaseArms extends CommandBase {

  private double velocity;
  private boolean done = false;
  Timer timer = new Timer();

  public ReleaseArms(double speed) {
    velocity = speed;
  }


  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    Elevator.resetEncoder();
  }

  @Override
  public void execute() {

    //center the turret to release the arms
    if(TurretedShooter.getHomePosition() == false){
        timer.start();
        if(!timer.hasElapsed(0.5)){
            TurretedShooter.turret.set(0.1);
        }
        else {
            TurretedShooter.turret.set(0.0);
            ClimbArms.open();
            done = true;
        }
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}



