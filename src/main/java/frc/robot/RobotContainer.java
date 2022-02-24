package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AUTO;
import frc.robot.Constants.CONTROLLER;
import frc.robot.Constants.SWERVE;
import frc.robot.commands.ClimbArmsCommand;
import frc.robot.commands.DesiredPositionCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.FeederCommand;
//import frc.robot.commands.FeederCommand;
// import frc.robot.commands.LimeLightTurretCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.DebouncedButton;

public class RobotContainer {

  public static DriveTrain driveTrain = new DriveTrain(2, 2);

  public static XboxController controller = new XboxController(CONTROLLER.PORT);
  public static JoystickButton a = new JoystickButton(controller, CONTROLLER.A),
  b = new JoystickButton(controller, CONTROLLER.B),
  leftBumber = new JoystickButton(controller, CONTROLLER.LEFT_BUMPER),
  rightBumber = new JoystickButton(controller, CONTROLLER.RIGHT_BUMPER),
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
    driveTrain.reset(true);
    driveTrain.setDefaultCommand(new DriveCommand(driveTrain, ()->-modifyAxis(controller.getLeftY()) * SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getLeftX())* SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getRightX())* SWERVE.MAX_ANGULAR));
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    start.whenPressed(new Runnable() {
      public void run() {
          if(start.get())driveTrain.reset(leftStick.get());
      }
    });

    

    // a.whenHeld(new LimeLightTurretCommand(true));
    // a.whenReleased(new LimeLightTurretCommand(false));
    // a.whenPressed(new ElevatorDownCommand(-0.2));

    y.whileHeld(new ElevatorCommand(0.5));
    a.whileHeld(new ElevatorCommand(-0.5));
    // x.whenPressed(new ClimbArmsCommand(140));
    leftBumber.whileHeld(new FeederCommand(0.8, 0.5));
    rightBumber.whileHeld(new FeederCommand(-0.5, 0.0));

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

  public Command getAutoCommand(){
    return new DesiredPositionCommand(driveTrain, Constants.AUTO.AUTO_TEST_X);
    // return new FollowPathCommand(driveTrain);
  }

}
