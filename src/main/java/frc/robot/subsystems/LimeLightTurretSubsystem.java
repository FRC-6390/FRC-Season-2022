package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants.SHOOTER;

public class LimeLightTurretSubsystem extends SubsystemBase{
    public boolean held, done;
    private NetworkTable table;
    public NetworkTableEntry pipeline, ledMode, camMode, txvalue, tyvalue;
    public double kP, tx, ty, heading_error, distance_error, rotation_adjust, distance_adjust, rotation, kpAim, kpDistance;
    private static CANSparkMax turretMotor;
    private static Encoder turretEncoder;
    private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
    private static DigitalInput turretSwitch;

    static{
      turretMotor = new CANSparkMax(SHOOTER.TURRET, MotorType.kBrushless);
      turretEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k2X);
      turretSwitch = new DigitalInput(0);
    }


    public LimeLightTurretSubsystem(){
      setMotorsIdleMode(IdleMode.kBrake);
      tab.getLayout("Shooter", BuiltInLayouts.kList).addNumber("Turret Encoder", ()->getTurretEncoder());
      tab.getLayout("Shooter", BuiltInLayouts.kList).addBoolean("Turret Switch", ()->getHomePosition());
    }

    public static void setMotorSpeed(double velocity){
      turretMotor.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
      turretMotor.setIdleMode(mode);
    }

    public static double getTurretEncoder(){
      return turretEncoder.getDistance();
    }

    public static boolean getHomePosition(){
      return turretSwitch.get();
    }


    public LimeLightTurretSubsystem(boolean isHeld) {
        held = isHeld;
        table = NetworkTableInstance.getDefault().getTable("limelight");
        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");
        txvalue = table.getEntry("tx");
        tyvalue = table.getEntry("ty");
        pipeline = table.getEntry("pipeline");
    }

    public static double getRecomendedVelocity(){
      //based of distance and network table
      return 0;
    }

    public void ChangePipeline(int Pipeline){
      pipeline.setDouble(Pipeline);
    }

    @Override
    public void periodic() {
      
    }
}