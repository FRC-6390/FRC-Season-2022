package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAndFeederCommand extends CommandBase {

  private double intakeVelocity, feederVelocity;
  private Leds led = new Leds();

  public IntakeAndFeederCommand(double intakeSpeed, double feederSpeed) {
    intakeVelocity = intakeSpeed;
    feederVelocity = feederSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    //setting intake and feeder speeds
    Intake.setMotorSpeed(intakeVelocity);
    Feeder.setMotorSpeed(feederVelocity);
    led.setBlue();
 
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