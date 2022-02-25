package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  RobotContainer container;

  @Override
  public void robotInit() {
    container = new RobotContainer();
    Robot.suppressExitWarning(true);
  }

  @Override
  public void robotPeriodic() {
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
    //starts auto routine
    CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().schedule(container.getDriveCommand());
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();

    //makes robot sing a song
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
