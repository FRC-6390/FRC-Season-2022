package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.CONTROLLER;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.DebouncedButton;

public class RobotContainer {

  private DriveTrain driveTrain = new DriveTrain(0, 0);

  public static XboxController controller = new XboxController(CONTROLLER.PORT);
  public static JoystickButton a = new JoystickButton(controller, CONTROLLER.A),
  b = new JoystickButton(controller, CONTROLLER.B),
  x = new JoystickButton(controller, CONTROLLER.X),
  y = new JoystickButton(controller, CONTROLLER.Y),
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
  start = new DebouncedButton(controller, CONTROLLER.START, CONTROLLER.DEBOUNCE_PERIOD);
  
  public RobotContainer() {
    driveTrain.setDefaultCommand(new DriveCommand(driveTrain, ()->-modifyAxis(controller.getLeftX()), ()->-modifyAxis(controller.getLeftY()), ()->-modifyAxis(controller.getRightX())));
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    start.whenPressed(new Runnable() {
      public void run() {
          if(start.get())driveTrain.reset(leftStick.get());
      }
    });
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) return ((value > 0.0) ? (value - deadband) : (value + deadband)) / (1.0 - deadband);
    return 0.0;
  }

  private static double modifyAxis(double value) {
    value = deadband(value, CONTROLLER.DEAD_ZONE);
    return Math.copySign(value * value, value);
  }

}
