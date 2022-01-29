package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DesiredPosition;

public class Robot extends TimedRobot {

  RobotContainer container;

  @Override
  public void robotInit() {
    container = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    if(RobotContainer.start.debounced()){
      RobotContainer.driveTrain.reset(RobotContainer.left.get());
    }
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
  public void testPeriodic() {}
}
