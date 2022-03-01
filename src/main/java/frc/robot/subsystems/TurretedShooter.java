package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SHOOTER;;

public class TurretedShooter extends SubsystemBase {

  private CANSparkMax turret, shooterLeft, shooterRight, preLeftShooter,preRightShooter;
  private DigitalInput rightLimit, leftLimit;
  private CANCoder shooterEncoder;
  private boolean auto = true;
  private double seekingTo = SHOOTER.TURRET_MAX;
  private double shooterStart = Timer.getFPGATimestamp();
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  


  public TurretedShooter() {
    turret = new CANSparkMax(SHOOTER.TURRET, MotorType.kBrushless);
    shooterLeft = new CANSparkMax(SHOOTER.LEFT, MotorType.kBrushless);
    shooterRight = new CANSparkMax(SHOOTER.RIGHT, MotorType.kBrushless);
    preLeftShooter = new CANSparkMax(SHOOTER.PRE_LEFT, MotorType.kBrushless);
    preRightShooter = new CANSparkMax(SHOOTER.PRE_RIGHT, MotorType.kBrushless);
    rightLimit = new DigitalInput(SHOOTER.RIGHT_LIMIT_SWITCH);
    leftLimit = new DigitalInput(SHOOTER.LEFT_LIMIT_SWITCH);
    shooterEncoder = new CANCoder(14);
    preRightShooter.follow(preLeftShooter, true);
    shooterLeft.follow(shooterRight, true);
    tab.getLayout("Shooter", BuiltInLayouts.kList).addBoolean("right limit", () -> rightLimit.get());
    tab.getLayout("Shooter", BuiltInLayouts.kList).addBoolean("left limit", () -> leftLimit.get());
  }

  //Will rotate the turret back and forth
  private boolean seek(){
    // if(!lock()){
    //   turret.set(0.1);
    //   if(leftLimit.get() || rightLimit.get()) turret.setInverted(!turret.getInverted()); 
    // }else{
    //   turret.set(0.0);
    //   return true;
    // }
    return true;
  }

  //Will check if limelight has found the goal and is currently looking at it
  private boolean lock(){
    return false;
  }

  //Will rev shooter to the velocity then shoot the ball, if the shooter doesnt reach the velocity in the given time it will fire anyways
  public void shoot(){
    // preLeftShooter.set(0.8);
    double speed = SHOOTER.SHOOTER_PID.calc(shooterEncoder.getVelocity(), -SHOOTER.VELOCITY)+0.1;
    speed = speed > 0 ? 0.1 : speed;
    shooterRight.set(speed);
    System.out.println(shooterEncoder.getVelocity() +" "+speed);
    if(SHOOTER.SHOOTER_PID.threshhold() || shooterStart < Timer.getFPGATimestamp()-SHOOTER.TIMEOUT ){
      preLeftShooter.set(1);
    }
  }

  public boolean isHome(){
    return rightLimit.get();
  }

  public void home(){
    if(!isHome()){
      //10 bit analog 2.6khz
    }
  }

  //Will cause the robot to look and auto shoot balls
  public void auto(){
    if(seek()) shoot();
  }

  public void setAutoMode(boolean auto){
    this.auto = auto;
  }



  @Override
  public void periodic() {
    if(auto) auto();
  }
}
