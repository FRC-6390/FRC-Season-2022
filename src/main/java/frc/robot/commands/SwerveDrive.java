package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {

  private XboxController controller;
  private SwerveDriveTrain swerveDrive;
  private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter angularLimiter = new SlewRateLimiter(3);

  public SwerveDrive(SwerveDriveTrain swerveDrive, XboxController controller) {
    this.controller = controller;
    this.swerveDrive = swerveDrive;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    double leftXAxis = -xSpeedLimiter.calculate(inDeadZone(controller.getLeftY()) ? 0 : controller.getLeftY() * Constants.SWERVE.PRECENT_MAX_SPEED);
    double leftYAxis = -ySpeedLimiter.calculate(inDeadZone(controller.getLeftX()) ? 0 : controller.getLeftX() * Constants.SWERVE.PRECENT_MAX_SPEED);

    double rightXAxis = -angularLimiter.calculate(inDeadZone(controller.getRightX()) ? 0 : controller.getRightX() * Constants.SWERVE.PRECENT_MAX_SPEED);

    swerveDrive.drive(leftXAxis  * Constants.SWERVE.PRECENT_MAX_SPEED, leftYAxis * Constants.SWERVE.PRECENT_MAX_SPEED, rightXAxis * Constants.SWERVE.PRECENT_MAX_SPEED, true);
  }

  @Override
  public void end(boolean interrupted) {
    swerveDrive.drive(0, 0, 0, false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private boolean inDeadZone(double value){
    return !(value >= Constants.CONTROLLER.XBOX.DEAD_ZONE_MAX || value <= Constants.CONTROLLER.XBOX.DEAD_ZONE_MIN);
  }
}
