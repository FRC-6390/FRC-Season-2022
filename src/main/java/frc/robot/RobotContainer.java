// package frc.robot;

// import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.commands.FeederCommand;
// import frc.robot.commands.IntakeCommand;
// import frc.robot.commands.LimeLightBallCommand;
// import frc.robot.commands.PreShooterCommand;
// import frc.robot.utils.DebouncedButton;
// import frc.robot.Constants.CONTROLLER.XBOX;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// public class RobotContainer {

//   public static XboxController controller = new XboxController(XBOX.PORT);
//   public static JoystickButton a = new JoystickButton(controller, XBOX.BUTTON.A),
//   b = new JoystickButton(controller, XBOX.BUTTON.B),
//   x = new JoystickButton(controller, XBOX.BUTTON.X),
//   y = new JoystickButton(controller, XBOX.BUTTON.Y),
//   leftBumber = new JoystickButton(controller, XBOX.BUTTON.LEFT_BUMPER),
//   rightBumber = new JoystickButton(controller, XBOX.BUTTON.RIGHT_BUMPER),
//   leftStick = new JoystickButton(controller, XBOX.BUTTON.LEFT_JOYSTICK),
//   rightStick = new JoystickButton(controller, XBOX.BUTTON.RIGHT_JOYSTICK),
//   // POV
//   top = new JoystickButton(controller, XBOX.POV.TOP),
//   topRight = new JoystickButton(controller, XBOX.POV.TOP_RIGHT),
//   topLeft = new JoystickButton(controller, XBOX.POV.TOP_LEFT),
//   bottom = new JoystickButton(controller, XBOX.POV.BOTTOM),
//   bottomRight = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
//   bottomLeft = new JoystickButton(controller, XBOX.POV.BOTTOM_LEFT),
//   right = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
//   left = new JoystickButton(controller, XBOX.POV.LEFT);

//   public static DebouncedButton back = new DebouncedButton(controller, XBOX.BUTTON.BACK, XBOX.DEBOUNCE_PERIOD),
//   start = new DebouncedButton(controller, XBOX.BUTTON.START, XBOX.DEBOUNCE_PERIOD);

//   public RobotContainer() {
//     configureButtonBindings();
//   }
 
//   private void configureButtonBindings() {
//     // start.debounced();

//     leftBumber.whenHeld(new IntakeCommand(false));
//     rightBumber.whenHeld(new IntakeCommand(true));

//     x.whenHeld(new FeederCommand(false));
//     y.whenHeld(new FeederCommand(true));

//     a.whenHeld(new LimeLightBallCommand(true));
//     a.whenReleased(new LimeLightBallCommand(false));
//   }

// }











// package frc.robot;

// import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.commands.FeederCommand;
// import frc.robot.commands.IntakeCommand;
// import frc.robot.commands.LimeLightBallCommand;
// import frc.robot.commands.PreShooterCommand;
// import frc.robot.utils.DebouncedButton;
// import frc.robot.Constants.CONTROLLER;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// public class RobotContainer {

//   public static XboxController controller = new XboxController(CONTROLLER.PORT);
//   public static JoystickButton a = new JoystickButton(controller, CONTROLLER.BUTTON.A),
//   b = new JoystickButton(controller, CONTROLLER.BUTTON.B),
//   x = new JoystickButton(controller, CONTROLLER.BUTTON.X),
//   y = new JoystickButton(controller, CONTROLLER.BUTTON.Y),
//   leftBumber = new JoystickButton(controller, XBOX.BUTTON.LEFT_BUMPER),
//   rightBumber = new JoystickButton(controller, XBOX.BUTTON.RIGHT_BUMPER),
//   leftStick = new JoystickButton(controller, XBOX.BUTTON.LEFT_JOYSTICK),
//   rightStick = new JoystickButton(controller, XBOX.BUTTON.RIGHT_JOYSTICK),
//   // POV
//   top = new JoystickButton(controller, XBOX.POV.TOP),
//   topRight = new JoystickButton(controller, XBOX.POV.TOP_RIGHT),
//   topLeft = new JoystickButton(controller, XBOX.POV.TOP_LEFT),
//   bottom = new JoystickButton(controller, XBOX.POV.BOTTOM),
//   bottomRight = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
//   bottomLeft = new JoystickButton(controller, XBOX.POV.BOTTOM_LEFT),
//   right = new JoystickButton(controller, XBOX.POV.BOTTOM_RIGHT),
//   left = new JoystickButton(controller, XBOX.POV.LEFT);

//   public static DebouncedButton back = new DebouncedButton(controller, XBOX.BUTTON.BACK, XBOX.DEBOUNCE_PERIOD),
//   start = new DebouncedButton(controller, XBOX.BUTTON.START, XBOX.DEBOUNCE_PERIOD);

//   public RobotContainer() {
//     configureButtonBindings();
//   }
 
//   private void configureButtonBindings() {
//     // start.debounced();

//     leftBumber.whenHeld(new IntakeCommand(false));
//     rightBumber.whenHeld(new IntakeCommand(true));

//     x.whenHeld(new FeederCommand(false));
//     y.whenHeld(new FeederCommand(true));

//     a.whenHeld(new LimeLightBallCommand(true));
//     a.whenReleased(new LimeLightBallCommand(false));
//   }

// }



package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AUTO;
import frc.robot.Constants.CONTROLLER;
import frc.robot.Constants.SWERVE;
import frc.robot.commands.DesiredPositionCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import frc.robot.utils.DebouncedButton;

public class RobotContainer {

  public static SwerveDriveTrain driveTrain = new SwerveDriveTrain(0, 0);

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
    driveTrain.reset(true);
    driveTrain.setDefaultCommand(new SwerveDriveCommand(driveTrain, ()->-modifyAxis(controller.getLeftY()) * SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getLeftX())* SWERVE.MAX_VELCOCITY, ()->-modifyAxis(controller.getRightX())* SWERVE.MAX_ANGULAR));
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
    return new DesiredPositionCommand(driveTrain, AUTO.AUTO_1_POSITIONS);
  }

}