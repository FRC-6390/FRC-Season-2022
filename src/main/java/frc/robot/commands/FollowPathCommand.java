package frc.robot.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class FollowPathCommand extends CommandBase{
  /** Creates a new GenericAutonomousCommand. */
  private final DriveTrain driveTrainSubsystem;
  private DoubleSupplier x, y, r;

  public FollowPathCommand(DriveTrain subsystem) {
    driveTrainSubsystem = subsystem;
  }

  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
        //test
        // This will load the file "Example Path.path" and generate it with a max velocity of 8 m/s and a max acceleration of 5 m/s^2
        PathPlannerTrajectory examplePath = PathPlanner.loadPath("New Path", 8, 2);

        // Sample the state of the path at 1.2 seconds
        // To access PathPlanner specific information, such as holonomic rotation, the state must be cast to a PathPlannerState
        PathPlannerState exampleState = (PathPlannerState) examplePath.sample(1.2);

        // Print the holonomic rotation at the sampled time
    System.out.println(exampleState.holonomicRotation.getDegrees());
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    driveTrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}