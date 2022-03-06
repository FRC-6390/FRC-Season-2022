package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ElevatorUpCommand extends CommandBase {

  private double velocity;
  private boolean done = false;
  private int revolutions = 4250;
  private int climbPercent;

  public ElevatorUpCommand(double speed, int perecent) {
    velocity = speed;
    climbPercent = perecent / 100;
  }


  @Override
  public void initialize() {
    Elevator.setMotorsIdleMode(IdleMode.kBrake);
    Elevator.resetEncoder();
    TurretedShooter.seeking = false;
  }

  @Override
  public void execute() {

    if(RobotContainer.start.debounced()) cancel();
    //rotate turret till limit switch is false
    if(TurretedShooter.getHomePosition() == true){
      TurretedShooter.turret.set(-0.1);
    }

    //bring the climber up so it can release from the bar
    if(TurretedShooter.getHomePosition() == false){
        TurretedShooter.turret.set(0.0);
        System.out.println("Relese the CLIMBER: " + Elevator.getEncoder());

        if(Elevator.getEncoder() >= -600){
            Elevator.setMotorSpeed(velocity);
        }else{
            Elevator.setMotorSpeed(0.0);
            System.out.println("CLimbing Finished");
            done = true;
        }
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.setMotorSpeed(0.0);
    // TurretedShooter.seeking = true;
    TurretedShooter.turret.set(0.0);
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}



