// package frc.robot.commands;

// import frc.robot.Constants;
// import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
// import edu.wpi.first.math.filter.SlewRateLimiter;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;

// public class SwerveDrive extends CommandBase {

//   private XboxController controller;
//   private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(3);
//   private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(3);
//   private final SlewRateLimiter angularLimiter = new SlewRateLimiter(3);

//   public SwerveDrive(XboxController controller) {
//     this.controller = controller;
//   }

//   @Override
//   public void initialize() {}

//   @Override
//   public void execute() {
    
//     double leftXAxis = xSpeedLimiter.calculate(inDeadZone(controller.getLeftY()) ? 0 : controller.getLeftY()) * Constants.SWERVE.PRECENT_MAX_SPEED;
//     double leftYAxis = ySpeedLimiter.calculate(inDeadZone(controller.getLeftX()) ? 0 : controller.getLeftX()) * Constants.SWERVE.PRECENT_MAX_SPEED;

//     double rightXAxis = angularLimiter.calculate(inDeadZone(controller.getRightX()) ? 0 : controller.getRightX()) * Constants.SWERVE.PRECENT_MAX_SPEED;

//     SwerveDriveTrain.drive(leftXAxis * Constants.SWERVE.PRECENT_MAX_SPEED, leftYAxis * Constants.SWERVE.PRECENT_MAX_SPEED, rightXAxis * Constants.SWERVE.PRECENT_MAX_SPEED, true);
//   }

//   @Override
//   public void end(boolean interrupted) {}

//   @Override
//   public boolean isFinished() {
//     return false;
//   }

//   private boolean inDeadZone(double value){
//     return !(value >= Constants.CONTROLLER.XBOX.DEAD_ZONE_MAX || value <= Constants.CONTROLLER.XBOX.DEAD_ZONE_MIN);
//   }
// }


package frc.robot.commands;

import java.lang.module.ModuleDescriptor.Requires;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;

public class SwerveDriveCommand extends CommandBase {

  private SwerveDriveTrain driveTrain;
  private DoubleSupplier x, y, r;
  private ShuffleboardTab tab = Shuffleboard.getTab("Contoller");

  public SwerveDriveCommand(SwerveDriveTrain subsystem, DoubleSupplier xSupplier, DoubleSupplier ySupplier, DoubleSupplier rSupplier) {
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
    if(DriverStation.isTeleop())driveTrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(x.getAsDouble(),0,r.getAsDouble(),driveTrain.rotation()));
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }
}