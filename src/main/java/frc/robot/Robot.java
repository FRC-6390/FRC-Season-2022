package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.PointToPoint;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SwerveDrive;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;

public class Robot extends TimedRobot {

  private SwerveDriveTrain swerveDrive = new SwerveDriveTrain();

  @Override
  public void robotInit() {
    swerveDrive.reset();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if(RobotContainer.back.debounced()) {
      System.out.println("backButton");
      swerveDrive.switchMotorMode();
    }
    if(RobotContainer.start.debounced()) {
      System.out.println("startButton");
      swerveDrive.resetPose(RobotContainer.leftStick.get());
    }
  }

  @Override
  public void disabledInit() {
    swerveDrive.setMotorMode(NeutralMode.Brake);
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
    //if(RobotContainer.back.get()) SwerveDriveTrain.switchMotorNeutralMode();
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(new PointToPoint(swerveDrive));
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    swerveDrive.reset();
    CommandScheduler.getInstance().schedule(new SwerveDrive(swerveDrive,RobotContainer.controller));
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
