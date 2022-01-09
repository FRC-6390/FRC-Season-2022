package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {

  private XboxController controller;
  private final SlewRateLimiter xSpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter ySpeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter angularLimiter = new SlewRateLimiter(3);

  public SwerveDrive(XboxController controller) {
    this.controller = controller;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    double leftXAxis = xSpeedLimiter.calculate(inDeadZone(controller.getLeftY()) ? 0 : controller.getLeftY() * Constants.SWERVE.PRECENT_MAX_SPEED);
    double leftYAxis = ySpeedLimiter.calculate(inDeadZone(controller.getLeftX()) ? 0 : controller.getLeftX() * Constants.SWERVE.PRECENT_MAX_SPEED);

    double rightXAxis = angularLimiter.calculate(inDeadZone(controller.getRightX()) ? 0 : controller.getRightX() * Constants.SWERVE.PRECENT_MAX_SPEED);

    SwerveDriveTrain.drive(leftXAxis, leftYAxis, rightXAxis, true);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private boolean inDeadZone(double value){
    return (value >= Constants.CONTROLLER.XBOX.DEAD_ZONE_MAX || value <= Constants.CONTROLLER.XBOX.DEAD_ZONE_MIN);
  }
}
