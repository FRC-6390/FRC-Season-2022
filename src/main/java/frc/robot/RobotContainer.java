package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.CONTROLLER;
import frc.robot.Constants.SHOOTER;
import frc.robot.Constants.SWERVE;
import frc.robot.commands.AutoElevator;
import frc.robot.commands.DesiredPositionCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ElevatorDownCommand;
import frc.robot.commands.ElevatorUpCommand;
import frc.robot.commands.IntakeAndFeederCommand;
import frc.robot.commands.ReleaseArms;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TurretMove;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TurretedShooter;
import frc.robot.subsystems.utils.DebouncedButton;

public class RobotContainer {
  public static DriveTrain driveTrain = new DriveTrain(0, 0);
  public static TurretedShooter turretedShooter = new TurretedShooter();

  public static XboxController controller = new XboxController(CONTROLLER.PORT);
  public static JoystickButton a = new JoystickButton(controller, CONTROLLER.A),
  b = new JoystickButton(controller, CONTROLLER.B),
  leftBumper = new JoystickButton(controller, CONTROLLER.LEFT_BUMPER),
  rightBumper = new JoystickButton(controller, CONTROLLER.RIGHT_BUMPER),
  leftStick = new JoystickButton(controller, CONTROLLER.LEFT_JOYSTICK),
  rightStick = new JoystickButton(controller, CONTROLLER.RIGHT_JOYSTICK),

  // POV
  top = new JoystickButton(controller, CONTROLLER.TOP),
  topRight = new JoystickButton(controller, CONTROLLER.TOP_RIGHT),
  topLeft = new JoystickButton(controller, CONTROLLER.TOP_LEFT),
  bottom = new JoystickButton(controller, CONTROLLER.BOTTOM),
  bottomRight = new JoystickButton(controller, CONTROLLER.BOTTOM_RIGHT),
  bottomLeft = new JoystickButton(controller, CONTROLLER.BOTTOM_LEFT),
  right = new JoystickButton(controller, CONTROLLER.BOTTOM_RIGHT),
  left = new JoystickButton(controller, CONTROLLER.LEFT);

  public static DebouncedButton back = new DebouncedButton(controller, CONTROLLER.BACK, CONTROLLER.DEBOUNCE_PERIOD),
  start = new DebouncedButton(controller, CONTROLLER.START, CONTROLLER.DEBOUNCE_PERIOD),
  x = new DebouncedButton(controller, CONTROLLER.X),
  y = new DebouncedButton(controller, CONTROLLER.Y);

  
  public RobotContainer() {
    driveTrain.setDefaultCommand(new DriveCommand(driveTrain, ()->-modifyAxis(controller.getLeftY()) * SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getLeftX())* SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getRightX())* SWERVE.MAX_ANGULAR));
    
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    x.whenPressed(() -> driveTrain.reset(true));
    // 
    
    
    b.whileHeld(new ShooterCommand(SHOOTER.LOW_VELOCITY));
    leftBumper.whileHeld(new IntakeAndFeederCommand(1, 0));   //intake and feeder
    rightBumper.whileHeld(new IntakeAndFeederCommand(-0.4, -0.4)); //reverse the intake
    y.whileHeld(new ElevatorCommand(0.3, true));
    a.whileHeld(new ElevatorCommand(-0.6,true));
    leftStick.whileHeld(new TurretMove(0.1));
    rightStick.whileHeld(new TurretMove(-0.1));
    back.whileHeld(() -> ClimbArms.close());
    start.whenPressed(() -> TurretedShooter.forceOff = !TurretedShooter.forceOff);
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, CONTROLLER.DEAD_ZONE);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  public Command getDriveCommand(){
    return driveTrain.getDefaultCommand();
  }

  //sets the auto command
  public Command getAutoCommand(){
    return new DesiredPositionCommand(driveTrain, Constants.AUTO.AUTO_1);
  }

}
