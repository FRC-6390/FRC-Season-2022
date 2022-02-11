package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLightTurretSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimeLightTurretCommand extends CommandBase {

  private LimeLightTurretSubsystem limelight;

  public boolean held, done;
  private NetworkTable table;
  public NetworkTableEntry pipeline, ledMode, camMode, txvalue, tavalue, tyvalue, tvvalue, tVertvalue, tHorvalue;
  public double tx, ta, ty, tv, heading_error, distance_error, rotation_adjust, distance_adjust, desiredArea, rotation, sideways, pAim, pDrive, pSideways, trackTarget, distance, height, angle;

  public LimeLightTurretCommand(LimeLightTurretSubsystem limelight) {
    this.limelight = limelight;
  }

  public LimeLightTurretCommand(boolean isHeld) {
    held = isHeld;
    table = NetworkTableInstance.getDefault().getTable("limelight");
    ledMode = table.getEntry("ledMode");
    camMode = table.getEntry("camMode");   
    txvalue = table.getEntry("tx");       //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    tyvalue = table.getEntry("ty");       //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    tvvalue = table.getEntry("tv");       //Whether the limelight has any valid targets (0 or 1)
    tavalue = table.getEntry("ta");       //Target Area (0% of image to 100% of image)
    tVertvalue = table.getEntry("thor");
    tHorvalue = table.getEntry("tvert");
  }

  @Override
  public void initialize() {
    // if (held == true){
      pAim = 0.002;
      rotation_adjust = 0.0;
      desiredArea = 2.0;
      tx = txvalue.getDouble(0.0);
      ty = tyvalue.getDouble(0.0);
      ta = tavalue.getDouble(0.0);
      camMode.setDouble(0.0);
      
      done = false;
    // }
    // else if(held == false){
    //   camMode.setDouble(1.0);
    //   done = true;
    // }
  }

  @Override
  public void execute() {
    tx = txvalue.getDouble(0.0);
    ty = tyvalue.getDouble(0.0);
    tv = tvvalue.getDouble(0.0);
    ta = tavalue.getDouble(0.0);


    // to find distance from target d = height / tanAngle of camera
    height = 2.6416 - 0.22/*Limelight height from ground*/; 
    angle = Math.toRadians(60 + ty);
    distance = height / Math.tan(angle); //change the hood based off distance and network table
    System.out.print("DISTANCE___________: " + distance);
    done = true;
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}