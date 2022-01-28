
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveCommand extends CommandBase {

  private DriveTrain driveTrain;
  private DoubleSupplier x, y, r;
  private ShuffleboardTab tab = Shuffleboard.getTab("Contoller");

  public DriveCommand(DriveTrain subsystem, DoubleSupplier xSupplier, DoubleSupplier ySupplier, DoubleSupplier rSupplier) {
    driveTrain = subsystem;
    x = xSupplier;
    y = ySupplier;
    r = rSupplier;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    tab.addNumber("X", x);
    tab.addNumber("Y", y);
    tab.addNumber("R", r);
    driveTrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(x.getAsDouble(),y.getAsDouble(),r.getAsDouble(),driveTrain.rotation()));
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
