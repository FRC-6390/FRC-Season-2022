package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.utils.PID;

public class Robot extends TimedRobot {

  RobotContainer container;

  @Override
  public void robotInit() {
    container = new RobotContainer();
    
    // RobotContainer.driveTrain.getGyro().calibrate();//
    System.out.println("Gyro Calibrated");
    Robot.suppressExitWarning(true);
  }

  @Override
  public void robotPeriodic() {
    if(RobotContainer.start.debounced()){
      RobotContainer.driveTrain.reset(RobotContainer.top.get());
      RobotContainer.controller.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
      RobotContainer.controller.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    }
    
    RobotContainer.controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
    RobotContainer.controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    CommandScheduler.getInstance().run();
    
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {
    // if(RobotContainer.a.get()){
    //   System.out.println("a");
    // }
  }

  @Override
  public void teleopInit() {
    //CommandScheduler.getInstance().schedule(container.getDriveCommand());
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
