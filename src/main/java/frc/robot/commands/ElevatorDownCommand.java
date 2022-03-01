package frc.robot.commands;

import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorDownCommand extends CommandBase {

  private double velocity;
  private boolean done = false;
  private int revolutions = 4250;
  private int climbPercent;

  public ElevatorDownCommand(double speed, int perecent) {
    velocity = speed;
    climbPercent = perecent / 100;
  }


  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    Elevator.resetEncoder();
  }

  @Override
  public void execute() {
    //check the limit switch
    System.out.println(Elevator.getBottomSwitch());
    if(Elevator.getBottomSwitch() == true){
        Elevator.setMotorSpeed(-0.1);

        //change to desired encoder position
        if(Elevator.getEncoder() >=  revolutions * climbPercent){
          System.out.println("Servos Released");
          ClimbArms.open();
        }
    } else {
        done = true;
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