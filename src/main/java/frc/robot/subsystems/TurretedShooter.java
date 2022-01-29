package frc.robot.subsystems;

import java.util.ArrayList;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SHOOTER;;

public class TurretedShooter extends SubsystemBase {

  private CANSparkMax turret, shooterLeft, shooterRight, preShooter;
  private boolean auto = false;
  private double seekingTo = SHOOTER.TURRET_MAX;
  private double shooterStart = Timer.getFPGATimestamp();


  public TurretedShooter() {
    turret = new CANSparkMax(SHOOTER.TURRET, MotorType.kBrushless);
    shooterLeft = new CANSparkMax(SHOOTER.LEFT, MotorType.kBrushless);
    shooterRight = new CANSparkMax(SHOOTER.RIGHT, MotorType.kBrushless);
    preShooter = new CANSparkMax(SHOOTER.PRE, MotorType.kBrushless);
    shooterRight.follow(shooterLeft, true);
  }

  //Will rotate the turret back and forth
  private void seek(){
    turret.set(SHOOTER.TURRET_PID.calculate(turret.getEncoder().getPosition(), seekingTo));
    if(SHOOTER.TURRET_PID.atSetpoint()) SHOOTER.TURRET_PID.setSetpoint(seekingTo == SHOOTER.TURRET_MAX ? SHOOTER.TURRET_MIN : SHOOTER.TURRET_MAX);
  }

  //Will check if limelight has found the goal and is currently looking at it
  private boolean lock(){
    return false;
  }

  //Will rev shooter to the velocity then shoot the ball, if the shooter doesnt reach the velocity in the given time it will fire anyways
  public void shoot(){
    angleHood();
    shooterLeft.set(SHOOTER.SHOOTER_PID.calculate(shooterLeft.getEncoder().getVelocity(), SHOOTER.HIGH_VELOCITY));
    if(SHOOTER.SHOOTER_PID.atSetpoint() || shooterStart < Timer.getFPGATimestamp()-SHOOTER.TIMEOUT ){
      preShooter.set(1);
    }
  }

  //Will angle hood for shot
  private double angleHood(){
    return 0;
  }

  private boolean hasBall(){
    return false;
  }

  //Will cause the robot to look and auto shoot balls
  public void auto(){
    if(!lock()) seek();
    else if(hasBall()) shoot();
  }

  public void setAutoMode(boolean auto){
    this.auto = auto;
  }

  @Override
  public void periodic() {
    if(auto) auto();
  }
}
