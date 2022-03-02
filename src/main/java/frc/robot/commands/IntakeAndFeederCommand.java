package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import frc.robot.subsystems.Leds.LED_COLOURS;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAndFeederCommand extends CommandBase {

  private double intakeVelocity, feederVelocity;

  public IntakeAndFeederCommand(double intakeSpeed, double feederSpeed) {
    intakeVelocity = intakeSpeed;
    feederVelocity = feederSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
      Intake.setMotorSpeed(intakeVelocity);
      Feeder.setMotorSpeed(feederVelocity);
      Leds.set(LED_COLOURS.Blue);
  }

  @Override
  public void end(boolean interrupted) {
    Feeder.setMotorSpeed(0.0);
    Intake.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}