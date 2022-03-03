package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class AutoElevator extends SequentialCommandGroup {
  
  public AutoElevator() {
    addCommands(
        // Drive forward the specified distance
        new ElevatorDownCommand(-0.1, 50),
        new ReleaseArms(0.1),
        new ElevatorUpCommand(0.1, 20)
    );

  }
}
