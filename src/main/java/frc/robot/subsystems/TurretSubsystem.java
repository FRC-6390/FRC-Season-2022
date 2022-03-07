package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TURRET;
import frc.robot.subsystems.LEDSubsystem.LEDColour;

public class TurretSubsystem extends SubsystemBase {
  
  static CANSparkMax kTurretMotor, kLeftPreShooterMotor, kRightPreShooterMotor, kLeftShooterMotor, kRightShooterMotor;
  static CANCoder kShooterEncoder;
  static DigitalInput kLeftLimitSwitch, kRightLimitSwitch;
  static NetworkTable kLimeLight;
  static boolean kSeeking = false;
  static double kTimeout = System.currentTimeMillis();
  static double kTimeoutSeconds = TURRET.TURRET_TIMEOUT * 1000;

  static {
    kTurretMotor = new CANSparkMax(TURRET.TURRET, MotorType.kBrushless);
    kLeftPreShooterMotor = new CANSparkMax(TURRET.PRE_LEFT, MotorType.kBrushless);;
    kRightPreShooterMotor = new CANSparkMax(TURRET.PRE_RIGHT, MotorType.kBrushless);;
    kLeftShooterMotor = new CANSparkMax(TURRET.LEFT, MotorType.kBrushless);;
    kRightShooterMotor = new CANSparkMax(TURRET.RIGHT, MotorType.kBrushless);;
    kShooterEncoder = new CANCoder(TURRET.ENCODER);
    kLeftLimitSwitch = new DigitalInput(TURRET.LEFT_LIMIT_SWITCH);
    kRightLimitSwitch = new DigitalInput(TURRET.RIGHT_LIMIT_SWITCH);
    kLimeLight = NetworkTableInstance.getDefault().getTable("limelight");

  }

  public static void setTurretSpeed(double speed){
    kTurretMotor.set(speed);
  }

  public static void setPreShooterSpeed(double speed) {
    kLeftPreShooterMotor.set(speed);
    kRightPreShooterMotor.set(-speed);
  }

  public static void setShooterSpeed(double speed) {
    kLeftShooterMotor.set(speed);
    kRightShooterMotor.set(-speed);
  }

  public static void stop(){
    stopPreShooter();
    kLeftShooterMotor.stopMotor();
    kRightShooterMotor.stopMotor();
  }

  public static double getVelocity(){
    return kShooterEncoder.getVelocity();
  }

  public static void stopPreShooter(){
    kLeftPreShooterMotor.stopMotor();
    kRightPreShooterMotor.stopMotor();
  }

  public static boolean getLeftLimit(){
    return !kLeftLimitSwitch.get();
  }

  public static boolean getRightLimit(){
    return !kRightLimitSwitch.get();
  }

  public static void setSeeking(boolean seeking){
    kSeeking = seeking;
  }

  public static boolean getSeeking(){
    return kSeeking;
  }

  private boolean atLimit(){
    return getLeftLimit() ||getRightLimit();
  }

  private boolean foundTarget(){
    return kLimeLight.getEntry("tv").getDouble(0.0) != 0.0;
  }

  private void invertDirection() {
    kTurretMotor.setInverted(!kTurretMotor.getInverted());
    updateTimeout();
  }

  private void updateTimeout(){
    kTimeout = System.currentTimeMillis() + kTimeoutSeconds;
  }

  private void findTarget(){
    if(kTimeout > System.currentTimeMillis()){
      kTurretMotor.set(0.2);
    }else if(atLimit()) invertDirection(); 
  }

  private static double getTX(){
    return kLimeLight.getEntry("tx").getDouble(0.0);
  }

  private void lockTarget(){
    if(atLimit()){
      kTurretMotor.set(0.0);
      updateTimeout();
    }
    kTurretMotor.setInverted(false);
    kTurretMotor.set(TURRET.TURRET_PID.calc(getTX()));    
  }

  private boolean targetLocked(){
    return getTX() <= TURRET.LOCK_THRESHOLD;
  }

  @Override
  public void periodic() {
    if(getSeeking()){
      if(!foundTarget()) findTarget();
      else lockTarget();
    }

    if(targetLocked()) LEDSubsystem.setValue(LEDColour.Green);
    else if(foundTarget()) LEDSubsystem.setValue(LEDColour.Yellow);
    else LEDSubsystem.setValue(LEDColour.Red);
  }

}
