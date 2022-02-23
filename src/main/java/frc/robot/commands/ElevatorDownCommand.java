package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Feeder;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorDownCommand extends CommandBase {

  private boolean end;
  private double velocity;

  public ElevatorDownCommand(double speed) {
    velocity = speed;
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    
    if(Elevator.getTopSwitch() == false && Elevator.getBottomSwitch()){
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