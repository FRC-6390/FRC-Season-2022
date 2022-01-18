package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import frc.robot.subsystems.vission.LimeLightSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimeLightTurret extends CommandBase {

  private LimeLightSubsystem limelight;

  public boolean held, done;
  private NetworkTable table;
  public NetworkTableEntry pipeline, ledMode, camMode, txvalue, tavalue, tyvalue, tvvalue, tVertvalue, tHorvalue;
  public double tx, ta, ty, tv, heading_error, distance_error, rotation_adjust, distance_adjust, desiredArea, rotation, sideways, pAim, pDrive, pSideways;

  public LimeLightTurret(LimeLightSubsystem limelight) {
    this.limelight = limelight;
  }

  public LimeLightTurret(boolean isHeld) {
    held = isHeld;
    table = NetworkTableInstance.getDefault().getTable("limelight");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");
    txvalue = table.getEntry("tx");
    tyvalue = table.getEntry("ty");
    tvvalue = table.getEntry("tv");
    tavalue = table.getEntry("ta");
    tVertvalue = table.getEntry("thor");
    tHorvalue = table.getEntry("tvert");
  }

  @Override
  public void initialize() {
    if (held == true){
      pAim = 0.002;
      rotation_adjust = 0.0;
      desiredArea = 2.0;
      tx = txvalue.getDouble(0.0);
      ty = tyvalue.getDouble(0.0);
      ta = tavalue.getDouble(0.0);
      camMode.setDouble(0.0);
      
      done = false;
    }
    else if(held == false){
      camMode.setDouble(1.0);
      done = true;
    }
  }

  @Override
  public void execute() {
    tx = txvalue.getDouble(0.0);
    ty = tyvalue.getDouble(0.0);
    tv = tvvalue.getDouble(0.0);
    ta = tavalue.getDouble(0.0);


    rotation = pAim * -tx;


    LimeLightSubsystem.setMotorSpeed(rotation * 0.5);

    //if position == encoder left, scan right


    //if position == encoder right, scan left

    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
