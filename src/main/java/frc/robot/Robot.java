package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SwerveDrive;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import frc.robot.RobotContainer;

public class Robot extends TimedRobot {

  @Override
  public void robotInit() {
    SwerveDriveTrain.resetAll();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if(RobotContainer.back.get()) {
      System.out.println("backButton");
      SwerveDriveTrain.switchMotorMode();
    }
    if(RobotContainer.start.get()) {
      System.out.println("startButton");
      SwerveDriveTrain.resetAll();
    }
  }

  @Override
  public void disabledInit() {
    SwerveDriveTrain.setMotorMode(NeutralMode.Brake);
  }

  @Override
  public void disabledPeriodic() {
    //if(RobotContainer.back.get()) SwerveDriveTrain.switchMotorNeutralMode();
  }

  @Override
  public void autonomousInit() {

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
