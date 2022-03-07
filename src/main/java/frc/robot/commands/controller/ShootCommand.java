package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TURRET;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShootCommand extends CommandBase {

    private boolean kEnableSeeking;
    private double kSpeed, kVelocity, kTimeout;

  public ShootCommand(double velocity){
    kVelocity = velocity;
  }

  @Override
  public void initialize() {
    kTimeout = System.currentTimeMillis() + (TURRET.SHOOTER_TIMEOUT*1000);
    kEnableSeeking = TurretSubsystem.getSeeking();
    TurretSubsystem.setSeeking(false);
  }

  @Override
  public void execute() {
    if(TurretSubsystem.getVelocity() > -kVelocity) {
        kSpeed -= 0.01;
    }else{
        kSpeed += 0.001;
    }
      TurretSubsystem.setShooterSpeed(kSpeed);
    if(kTimeout < System.currentTimeMillis()){
        TurretSubsystem.setPreShooterSpeed(1);
        FeederSubsystem.setFeederSpeed(0.7);
    }else{
        TurretSubsystem.stopPreShooter();
    }
  
  }

  @Override
  public void end(boolean interrupted) {
    TurretSubsystem.stop();
    TurretSubsystem.setSeeking(kEnableSeeking);
  }

  @Override
  public boolean isFinished() {
      return false;
  }
}