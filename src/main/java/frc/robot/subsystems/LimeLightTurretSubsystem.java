package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.SHOOTER;

public class LimeLightTurretSubsystem extends SubsystemBase{
    public boolean held, done;
    private NetworkTable table;
    public NetworkTableEntry pipeline, ledMode, camMode, txvalue, tyvalue;
    public double kP, tx, ty, heading_error, distance_error, rotation_adjust, distance_adjust, rotation, kpAim, kpDistance;
    private static CANSparkMax turretMotor;

    static{
      turretMotor = new CANSparkMax(SHOOTER.TURRET, MotorType.kBrushless);
    }


    public LimeLightTurretSubsystem(){
      setMotorsIdleMode(IdleMode.kBrake);
    }

    public static void setMotorSpeed(double velocity){
      turretMotor.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
      turretMotor.setIdleMode(mode);
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