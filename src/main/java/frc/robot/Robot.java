package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.commands.PointToPoint;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SwerveDriveCommand;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;

public class Robot extends TimedRobot {

  RobotContainer container;

  @Override
  public void robotInit() {
    // new SwerveDriveTrain();
    // SwerveDriveTrain.resetAll();

    container = new RobotContainer();
    
    RobotContainer.driveTrain.getGyro().calibrate();
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
    // SwerveDriveTrain.setMotorMode(NeutralMode.Brake);
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {
    //if(RobotContainer.back.get()) SwerveDriveTrain.switchMotorNeutralMode();
  }

  @Override
  public void autonomousInit() {
    // SwerveDriveTrain.resetAll();
    // CommandScheduler.getInstance().schedule(new PointToPoint());
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // SwerveDriveTrain.resetAll();
    // CommandScheduler.getInstance().schedule(new SwerveDrive(RobotContainer.controller));
  }

  @Override
  public void teleopPeriodic() {
    // if(RobotContainer.controller.getLeftTriggerAxis() >= Constants.CONTROLLER.XBOX.THRESHOLD){
    //   CommandScheduler.getInstance().schedule(new ShooterCommand(false));
    // }

    // if(RobotContainer.controller.getRightTriggerAxis() >= Constants.CONTROLLER.XBOX.THRESHOLD){
    //   CommandScheduler.getInstance().schedule(new ShooterCommand(true));
    // }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
