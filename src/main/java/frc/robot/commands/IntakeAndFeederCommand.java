package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAndFeederCommand extends CommandBase {

  private double intakeVelocity, feederVelocity;
  private boolean intakeAction;

  public IntakeAndFeederCommand(double intakeSpeed, double feederSpeed, boolean action) {
    intakeVelocity = intakeSpeed;
    feederVelocity = feederSpeed;
    intakeAction = action;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    //if its true both the intake and feeder run
    if(intakeAction == true){
      Intake.setMotorSpeed(intakeVelocity);
      Feeder.setMotorSpeed(feederVelocity);
    }

    //intake will only run
    else{
      Intake.setMotorSpeed(intakeVelocity);
    }
  }

  @Override
  public void end(boolean interrupted) {
    Feeder.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}