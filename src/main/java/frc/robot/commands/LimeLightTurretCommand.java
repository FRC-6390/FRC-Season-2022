package frc.robot.commands;

import frc.robot.subsystems.LimeLightTurretSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
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
      // pAim = 0.002;
      // rotation_adjust = 0.0;
      // desiredArea = 2.0;
      // tx = txvalue.getDouble(0.0);
      // ty = tyvalue.getDouble(0.0);
      // ta = tavalue.getDouble(0.0);
  }

  @Override
  public void execute() {
    // tx = txvalue.getDouble(0.0);
    // ty = tyvalue.getDouble(0.0);
    // tv = tvvalue.getDouble(0.0);
    // ta = tavalue.getDouble(0.0);

  }

  @Override
  public void end(boolean interrupted) {
    LimeLightTurretSubsystem.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}