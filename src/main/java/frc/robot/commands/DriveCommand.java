
package frc.robot.commands;

import java.lang.module.ModuleDescriptor.Requires;
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
    addRequirements(subsystem);
    tab.addNumber("X Axis", x);
    tab.addNumber("Y Axis", y);
    tab.addNumber("R Axis", r);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    driveTrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(x.getAsDouble(),y.getAsDouble(),r.getAsDouble(),driveTrain.rotation()));
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }
}
