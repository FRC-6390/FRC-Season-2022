package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.AUTONOMOUS;
import frc.robot.Constants.TURRET;
import frc.robot.commands.autonomous.DesiredPositionAutoCommand;
import frc.robot.commands.controller.ClimbCommand;
import frc.robot.commands.controller.IntakeCommand;
import frc.robot.commands.controller.ShootCommand;
import frc.robot.commands.controller.SwerveDriveCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import utilities.controllib.controller.DebouncedXboxController;

public class RobotContainer {

  public static DebouncedXboxController controller = new DebouncedXboxController(0);

  private static SwerveDriveSubsystem swerveDriveSubsystem = new SwerveDriveSubsystem();
  private static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  private static FeederSubsystem feederSubsystem = new FeederSubsystem();
  private static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private static LEDSubsystem ledSubsystem = new LEDSubsystem();
  private static TurretSubsystem turretSubsystem = new TurretSubsystem();

  public RobotContainer() {
    swerveDriveSubsystem.setDefaultCommand(new SwerveDriveCommand(controller::LeftX, controller::LeftY, controller::RightX));
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    controller.RightTrigger().whileHeld(new ShootCommand(TURRET.HIGH_VELOCITY));
    controller.RightBumper().whenPressed(() -> TurretSubsystem.setSeeking(!TurretSubsystem.getSeeking()));
    controller.LeftTrigger().whileHeld(new IntakeCommand(0.7, 0.7));
    controller.LeftBumper().whileHeld(new IntakeCommand(-0.7, -0.7));
    controller.Y().whileHeld(new ClimbCommand(0.8));
    controller.A().whileHeld(new ClimbCommand(-0.8));
  }

  public Command getAutoCommand(){
    return new DesiredPositionAutoCommand(AUTONOMOUS.AUTO_1);
  }

  public static Subsystem getSwerveDriveSubsystem(){
    return swerveDriveSubsystem;
  }

  public static Subsystem getClimberSubsystem(){
    return climberSubsystem;
  }

  public static Subsystem getFeederSubsystem(){
    return feederSubsystem;
  }

  public static Subsystem getIntakeSubsystem(){
    return intakeSubsystem;
  }

  public static Subsystem getLEDSubsystem(){
    return ledSubsystem;
  }

  public static Subsystem getTurretSubsystem(){
    return turretSubsystem;
  }

}
