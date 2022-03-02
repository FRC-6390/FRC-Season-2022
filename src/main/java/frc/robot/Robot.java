package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.CANAPIJNI;
import edu.wpi.first.hal.simulation.DriverStationDataJNI;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.SystemsTest;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TurretedShooter;

public class Robot extends TimedRobot {

  RobotContainer container;
  
  @Override
  public void robotInit() {
    //container = new RobotContainer();
    Robot.suppressExitWarning(true);
    ClimbArms.close();
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
   // CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // ClimbArms.close();
    CommandScheduler.getInstance().schedule(new SystemsTest());
    // CommandScheduler.getInstance().schedule(container.getDriveCommand());
  }


  @Override
  public void teleopPeriodic() {
    
      // for (int i = 0; i < m0_7.length; i++) {
      //   m0_7[i].set(ControlMode.PercentOutput, speed);
      //   Timer.delay(1);
      //       m0_7[i].set(ControlMode.PercentOutput, 0.0);
      // }
  
      // for (int i = 0; i < m15_22.length; i++) {
      //   m15_22[i].set(speed);
      //   Timer.delay(1);
      //     m15_22[i].set(0.0);
      // }
  
      // for (int i = 0; i < pwm3_4.length; i++) {
      //   pwm3_4[i].set(speed);
      //   Timer.delay(1);
      //     pwm3_4[i].set(0.0);
      // }
    }  

  @Override
  public void testInit() {
    //makes robot sing a song
    Orchestra midi = new Orchestra();
    midi.loadMusic("/home/lvuser/deploy/champ.chrp");
    midi.addInstrument(new TalonFX(0, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(1, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(2, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(3, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(4, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(5, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(6, "Swerve CANivore"));
    midi.addInstrument(new TalonFX(7, "Swerve CANivore"));

    midi.play();
  }
  
  @Override
  public void testPeriodic() {

  } 
}
