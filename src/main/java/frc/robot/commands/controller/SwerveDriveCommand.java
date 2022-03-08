package frc.robot.commands.controller;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class SwerveDriveCommand extends CommandBase {

  private DoubleSupplier x, y, r;

  public SwerveDriveCommand(DoubleSupplier xSupplier, DoubleSupplier ySupplier, DoubleSupplier rSupplier) {
    x = xSupplier;
    y = ySupplier;
    r = rSupplier;
    addRequirements(RobotContainer.getSwerveDriveSubsystem());
  }

  @Override
  public void initialize() {
    // require(RobotContainer.getSwerveDriveSubsystem());
  }

  @Override
  public void execute() {
    if(!DriverStation.isAutonomousEnabled())SwerveDriveSubsystem.drive(ChassisSpeeds.fromFieldRelativeSpeeds(x.getAsDouble(),y.getAsDouble(), r.getAsDouble(),SwerveDriveSubsystem.rotation()));
  }

  @Override
  public void end(boolean interrupted) {
    SwerveDriveSubsystem.stop();
  }
}
