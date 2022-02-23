package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DesiredPositionCommand;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.utils.PID;

public class Robot extends TimedRobot {

  RobotContainer container;
  @Override
  public void robotInit() {
   container = new RobotContainer();
    
    //RobotContainer.driveTrain.getGyro();//
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
    CommandScheduler.getInstance().schedule(container.getDriveCommand());

    // if(RobotContainer.x.debounced()){
    //   RobotContainer.driveTrain.updateOffsets();
    // }

    // if(RobotContainer.y.debounced()){
    //   RobotContainer.driveTrain.updateOffsets(0,0,0,0);
    // }
    
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    Orchestra midi = new Orchestra();
    midi.loadMusic("/home/lvuser/deploy/all star.chrp");
    midi.addInstrument(new TalonFX(0));
    midi.addInstrument(new TalonFX(1));
    midi.addInstrument(new TalonFX(2));
    midi.addInstrument(new TalonFX(3));
    midi.addInstrument(new TalonFX(4));
    midi.addInstrument(new TalonFX(5));
    midi.addInstrument(new TalonFX(6));
    midi.addInstrument(new TalonFX(7));

    midi.play();
  }

  @Override
  public void testPeriodic() {
    
  }
    
}
