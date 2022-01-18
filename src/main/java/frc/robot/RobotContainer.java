package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LimeLightCommand;
import frc.robot.utils.DebouncedButton;
import frc.robot.Constants.CONTROLLER.XBOX;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  public static XboxController controller = new XboxController(XBOX.PORT);
  public static JoystickButton a = new JoystickButton(controller, XBOX.BUTTON.A),
  b = new JoystickButton(controller, XBOX.BUTTON.B),
  x = new JoystickButton(controller, XBOX.BUTTON.X),
  y = new JoystickButton(controller, XBOX.BUTTON.Y),
  leftBumber = new JoystickButton(controller, XBOX.BUTTON.LEFT_BUMPER),
  rightBumber = new JoystickButton(controller, XBOX.BUTTON.RIGHT_BUMPER),
  leftStick = new JoystickButton(controller, XBOX.BUTTON.LEFT_JOYSTICK),
  rightStick = new JoystickButton(controller, XBOX.BUTTON.RIGHT_JOYSTICK),
  // POV
  top = new JoystickButton(controller, XBOX.POV.TOP),
  topRight = new JoystickButton(controller, XBOX.POV.TOP_RIGHT),
  topLeft = new JoystickButton(controller, XBOX.POV.TOP_LEFT),
  bottom = new JoystickButton(controller, XBOX.POV.BOTTOM),
  bottomRight = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
  bottomLeft = new JoystickButton(controller, XBOX.POV.BOTTOM_LEFT),
  right = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
  left = new JoystickButton(controller, XBOX.POV.LEFT);

  public static DebouncedButton back = new DebouncedButton(controller, XBOX.BUTTON.BACK, XBOX.DEBOUNCE_PERIOD),
  start = new DebouncedButton(controller, XBOX.BUTTON.START, XBOX.DEBOUNCE_PERIOD);

  public RobotContainer() {
    configureButtonBindings();
  }
 
  private void configureButtonBindings() {
    leftBumber.whenHeld(new IntakeCommand(false));
    // start.debounced();
    rightBumber.whenHeld(new IntakeCommand(true));

    a.whenHeld(new LimeLightCommand(true));
    a.whenReleased(new LimeLightCommand(false));
  }

}
