package frc.robot.commands;

import frc.robot.subsystems.ClimbArms;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.TurretedShooter;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.ReleaseArms;

public class ElevatorDownCommand extends CommandBase {

  private double velocity;
  private boolean done = false;
  private int revolutions = 4250;
  private int climbPercent;
  private int releaseArms = revolutions * climbPercent;

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

    //rotate turret till limit switch is false
    if(TurretedShooter.getHomePosition() == true){
      TurretedShooter.turret.set(-0.1);
    }

    //if the turret is homed begin climb
    if(TurretedShooter.getHomePosition() == false){

      //check the limit switch
      System.out.println(Elevator.getBottomSwitch());
      if(Elevator.getBottomSwitch() == true){
          Elevator.setMotorSpeed(-0.1);

          //change to desired encoder position
          if(Elevator.getEncoder() >= releaseArms){
            System.out.println("Servos Released");
            // new ReleaseArms()
          }
      } 
      else{
        done = true;
      }
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