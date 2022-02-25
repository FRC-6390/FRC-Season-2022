package frc.robot.commands;

import frc.robot.subsystems.Elevator;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorCommand extends CommandBase {

  private double velocity;
  private boolean automateElevator;

  public ElevatorCommand(double speed, boolean automateAction) {
    velocity = speed;
    automateElevator = automateAction;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    Elevator.setMotorsIdleMode(IdleMode.kBrake);

    if(automateElevator == true){
      //moves the elevator down until it is fully retracted
      if(Elevator.getTopSwitch() == false && Elevator.getBottomSwitch() == false){
        Elevator.setMotorSpeed(velocity);
      } else Elevator.setMotorSpeed(0.0);
    }
    else{
      Elevator.setMotorSpeed(velocity);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}