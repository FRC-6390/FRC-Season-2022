package frc.robot.commands;

import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorDownCommand extends CommandBase {

  private double velocity;
  private boolean done;

  public ElevatorDownCommand(double speed, boolean isDone) {
    velocity = speed;
    done = isDone;
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //check the limit switch
    if(Elevator.getBottomSwitch() == true){
      Elevator.setMotorSpeed(-0.1);
    } else Elevator.setMotorSpeed(0.0);

    //change to desired encoder position
    if(Elevator.getEncoder() >=  -2300){
      ClimbArms.open();
    }
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}