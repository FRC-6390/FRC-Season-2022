package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SwerveDrive;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;

public class Robot extends TimedRobot {

  private Command autonomousCommand;

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if(RobotContainer.controller.getBackButton()) SwerveDriveTrain.switchMotorNeutralMode();
    if(RobotContainer.controller.getStartButton()) SwerveDriveTrain.resetRobotPosition(RobotContainer.controller.getLeftStickButtonPressed());
  }

  @Override
  public void disabledInit() {
    SwerveDriveTrain.setMotorNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = RobotContainer.getAutonomousCommand();

    // schedule the autonomous command
    if(autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
   CommandScheduler.getInstance().schedule(new SwerveDrive(RobotContainer.controller));
  }

  @Override
  public void teleopPeriodic() {
    if(RobotContainer.controller.getLeftTriggerAxis() >= Constants.CONTROLLER.XBOX.THRESHOLD){
      CommandScheduler.getInstance().schedule(new ShooterCommand(false));
    }

    if(RobotContainer.controller.getRightTriggerAxis() >= Constants.CONTROLLER.XBOX.THRESHOLD){
      CommandScheduler.getInstance().schedule(new ShooterCommand(true));
    }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
