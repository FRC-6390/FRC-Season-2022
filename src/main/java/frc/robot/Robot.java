package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.CANAPIJNI;
import edu.wpi.first.hal.simulation.DriverStationDataJNI;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ClimbArms;

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
    // ClimbArms.close();
    CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // ClimbArms.close();
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
    midi.loadMusic("/home/lvuser/deploy/champ.chrp");
    midi.addInstrument(new TalonFX(0, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(1, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(2, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(3, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(4, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(5, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(6, "Swerve CANivore"));
    // midi.addInstrument(new TalonFX(7, "Swerve CANivore"));

    midi.play();
  }

  @Override
  public void testPeriodic() {
    
  }
    
}
