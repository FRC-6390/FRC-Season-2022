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

public class Robot extends TimedRobot {

  RobotContainer container;
  
  @Override
  public void robotInit() {
    container = new RobotContainer();
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
   // CommandScheduler.getInstance().schedule(container.getDriveCommand());
  //  m0 = new TalonFX(0, "Swerve CANivore");
  //  m1 = new TalonFX(1, "Swerve CANivore");
  //  m2 = new TalonFX(2, "Swerve CANivore");
  //  m3 = new TalonFX(3, "Swerve CANivore");
  //  m4 = new TalonFX(4, "Swerve CANivore");
  //  m5 = new TalonFX(5, "Swerve CANivore");
  //  m6 = new TalonFX(6, "Swerve CANivore");
  //  m7 = new TalonFX(7, "Swerve CANivore");

  //  m15 = new CANSparkMax(15, MotorType.kBrushless);
  //  m16 = new CANSparkMax(16, MotorType.kBrushless);
  //  m17 = new CANSparkMax(17, MotorType.kBrushless);
  //   m18 = new CANSparkMax(18, MotorType.kBrushless);
  //  m19 = new CANSparkMax(19, MotorType.kBrushless);
  //  m20 = new CANSparkMax(20, MotorType.kBrushless);
  //  m21 = new CANSparkMax(21, MotorType.kBrushless);
  //  m22 = new CANSparkMax(22, MotorType.kBrushless);

  //  pwm3 = new PWMSparkMax(3);
  //  pwm4 = new PWMSparkMax(4);

  //  m0_7 = new TalonFX[]{m0,m1,m2,m3,m4,m5,m6,m7};
  //  m15_22 = new CANSparkMax[]{m15,m16,m17,m18,m19,m20,m21,m22};
  //  pwm3_4 = new PWMSparkMax[] {pwm3, pwm4};
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
