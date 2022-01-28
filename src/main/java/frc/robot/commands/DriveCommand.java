
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveCommand extends CommandBase {

  private DriveTrain driveTrain;
  private DoubleSupplier x, y, r;

  public DriveCommand(DriveTrain subsystem, DoubleSupplier xSupplier, DoubleSupplier ySupplier, DoubleSupplier rSupplier) {
    driveTrain = subsystem;
    x = xSupplier;
    y = ySupplier;
    r = rSupplier;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
