package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class Robot extends TimedRobot {

  public static RobotContainer container;
  private static UsbCamera kUsbCamera;
  
  @Override
  public void robotInit() {
    container = new RobotContainer();
    kUsbCamera = CameraServer.startAutomaticCapture(0);
    CameraServer.getServer();
    kUsbCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
  }

  @Override
  public  void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    SwerveDriveSubsystem.reset();
  }

  @Override
  public void teleopPeriodic() {}  

  @Override
  public void testInit() {}
  
  @Override
  public void testPeriodic() {} 
}
