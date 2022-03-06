package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import frc.robot.subsystems.Leds.LED_COLOURS;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeOutCommand extends CommandBase {


  public IntakeOutCommand() {
   
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
      Intake.setMotorSpeed(1);
      Feeder.setMotorSpeed(1);

      Timer.delay(5);
  }

  @Override
  public void end(boolean interrupted) {
    Feeder.setMotorSpeed(0.0);
    Intake.setMotorSpeed(0.0);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}