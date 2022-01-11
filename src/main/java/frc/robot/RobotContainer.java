package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SwerveDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  public static XboxController controller = new XboxController(Constants.CONTROLLER.XBOX.PORT);
  public JoystickButton a = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.A),
  b = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.B),
  x = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.X),
  y = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.Y),
  leftBumber = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.LEFT_BUMPER),
  rightBumber = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.RIGHT_BUMPER),
  back = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.BACK),
  start = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.START),
  leftStick = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.LEFT_JOYSTICK),
  rightStick = new JoystickButton(controller, Constants.CONTROLLER.XBOX.BUTTON.RIGHT_JOYSTICK),
  // POV
  top = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.TOP),
  topRight = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.TOP_RIGHT),
  topLeft = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.TOP_LEFT),
  bottom = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.BOTTOM),
  bottomRight = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.BOTTOM_RIGHT),
  bottomLeft = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.BOTTOM_LEFT),
  right = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.BOTTOM_RIGHT),
  left = new JoystickButton(controller, Constants.CONTROLLER.XBOX.POV.LEFT);

  public RobotContainer() {
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    leftBumber.whenHeld(new IntakeCommand(false));
    rightBumber.whenHeld(new IntakeCommand(true));
    
  }

}
