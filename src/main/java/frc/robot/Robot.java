package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SwerveDrive;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;

public class Robot extends TimedRobot {

  private Command autonomousCommand;
  Debouncer debouncerStart = new Debouncer(0.1, DebounceType.kFalling);
  Debouncer debouncerBack = new Debouncer(0.1, DebounceType.kFalling);

  @Override
  public void robotInit() {
    SwerveDriveTrain.resetAll();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    if(debouncerBack.calculate(RobotContainer.controller.getBackButton())) {
      System.out.println("backButton");
      SwerveDriveTrain.switchMotorNeutralMode();
    }
    if(debouncerStart.calculate(RobotContainer.controller.getStartButton())) {
      System.out.println("startButton");
      SwerveDriveTrain.resetAll();
    }
  }

  @Override
  public void disabledInit() {
    SwerveDriveTrain.setMotorNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void disabledPeriodic() {
    if(RobotContainer.controller.getBackButton()) SwerveDriveTrain.switchMotorNeutralMode();
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
