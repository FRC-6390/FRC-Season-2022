package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.SHOOTER;
import frc.robot.commands.IntakeAndFeederCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SystemsTest;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {

  RobotContainer container;
  ShooterCommand shooterCommand = new ShooterCommand(SHOOTER.HIGH_VELOCITY);
  IntakeAndFeederCommand intakeCommand = new IntakeAndFeederCommand(1, 0.7);
  
  @Override
  public void robotInit() {
    container = new RobotContainer();
    Robot.suppressExitWarning(true);
    //ClimbArms.close();
    UsbCamera cam = CameraServer.startAutomaticCapture(0);
    CameraServer.getServer();

    cam.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    // PortForwarder.add(5800, "10.63.90.38",5800);
    // PortForwarder.add(5801, "10.63.90.38",5801);

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
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {
    //starts auto routine
    RobotContainer.driveTrain.reset(true);
    ClimbArms.open();
    CommandScheduler.getInstance().schedule(container.getAutoCommand());
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    ClimbArms.open();
   // CommandScheduler.getInstance().schedule(new SystemsTest());
    // CommandScheduler.getInstance().schedule(container.getDriveCommand());
    RobotContainer.driveTrain.reset(true);
  }


  @Override
  public void teleopPeriodic() {
      if(RobotContainer.controller.getLeftTriggerAxis() >0.5) CommandScheduler.getInstance().schedule(intakeCommand);
      else CommandScheduler.getInstance().cancel(intakeCommand);

      if(RobotContainer.controller.getRightTriggerAxis() >0.5) CommandScheduler.getInstance().schedule(shooterCommand);
      else CommandScheduler.getInstance().cancel(shooterCommand);

      // ClimbArms.open();
      // Timer.delay(3);
      // ClimbArms.close();
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
