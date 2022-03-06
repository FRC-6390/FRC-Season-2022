package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
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
import frc.robot.Constants.SHOOTER;
import frc.robot.subsystems.Leds.LED_COLOURS;;

public class TurretedShooter extends SubsystemBase {

  public static CANSparkMax turret, shooterLeft, shooterRight, preLeftShooter,preRightShooter;
  private static DigitalInput rightLimit, leftLimit;
  private static NetworkTable limelight;  public static CANCoder shooterEncoder;
  public static boolean seeking = false, forceOff = false;
  private static double shooterStart = Timer.getFPGATimestamp();
  private static ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  

  static {
    turret = new CANSparkMax(SHOOTER.TURRET, MotorType.kBrushless);
    shooterLeft = new CANSparkMax(SHOOTER.LEFT, MotorType.kBrushless);
    shooterRight = new CANSparkMax(SHOOTER.RIGHT, MotorType.kBrushless);
    preLeftShooter = new CANSparkMax(SHOOTER.PRE_LEFT, MotorType.kBrushless);
    preRightShooter = new CANSparkMax(SHOOTER.PRE_RIGHT, MotorType.kBrushless);
    rightLimit = new DigitalInput(SHOOTER.RIGHT_LIMIT_SWITCH);
    leftLimit = new DigitalInput(SHOOTER.LEFT_LIMIT_SWITCH);
    shooterEncoder = new CANCoder(14);
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
    tab.getLayout("Shooter", BuiltInLayouts.kList).addBoolean("right limit", () -> rightLimit.get());
    tab.getLayout("Shooter", BuiltInLayouts.kList).addBoolean("left limit", () -> leftLimit.get());
  }

  public TurretedShooter() {
  }
  

  //Will rotate the turret back and forth
  private static double timeout = System.currentTimeMillis();
  private static double seconds = 0.5 * 1000;
  private static boolean seek(){
    if(!seeTarget() || timeout > System.currentTimeMillis()){
      turret.set(0.2);
      if((!leftLimit.get() || !rightLimit.get()) && timeout < System.currentTimeMillis()) {
        turret.setInverted(!turret.getInverted()); 
        timeout = System.currentTimeMillis() + seconds;
      }
      Leds.set(LED_COLOURS.Red);
    }else{
      Leds.set(LED_COLOURS.Yellow);
      return lock();
    }
    return false;
  }

  //Will check if limelight has found the goal and is currently looking at it
  private static boolean lock(){
    double tv = limelight.getEntry("tv").getDouble(0.0);
    double tx = limelight.getEntry("tx").getDouble(0.0);

    if(tv == 0) return false;
    
    if((!leftLimit.get() || !rightLimit.get())){
      turret.set(0.0);
      timeout = System.currentTimeMillis() + seconds;
      return false;
    }
    turret.setInverted(false);
    turret.set(SHOOTER.TURRET_PID.calc(tx, 0));
    
    return SHOOTER.TURRET_PID.threshhold();
  }

  public static boolean seeTarget(){
    return limelight.getEntry("tv").getDouble(0.0) != 0;
  }

  public static boolean isHome(){
    return !rightLimit.get();
  }

  public static void home(){
    seeking = false;
    if(!isHome()){
      turret.set(0.1);
      if((!leftLimit.get()) && timeout < System.currentTimeMillis()) {
        turret.setInverted(!turret.getInverted()); 
        timeout = System.currentTimeMillis() + seconds;
      }
    }else{
      turret.set(0.0);
    }
  }

  public static boolean getHomePosition(){
    return leftLimit.get();
  }

  @Override
  public void periodic() {
    if(seeking && !forceOff)if(seek()) Leds.set(LED_COLOURS.Green);
    
    if(!seeking) turret.set(0.0);
  }
}
