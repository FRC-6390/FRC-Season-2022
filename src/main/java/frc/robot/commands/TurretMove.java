package frc.robot.commands;

import frc.robot.Constants.SHOOTER;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import frc.robot.subsystems.TurretedShooter;
import frc.robot.subsystems.Leds.LED_COLOURS;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretMove extends CommandBase {

  private double speed;

  public TurretMove(double speed) {
      this.speed = speed;
  }

  @Override
  public void initialize() {
    TurretedShooter.seeking = false;
    TurretedShooter.turret.setInverted(false);

  }

  @Override
  public void execute() {
    TurretedShooter.turret.set(speed);
  }

  @Override
  public void end(boolean interrupted) {
    TurretedShooter.turret.set(0.0);
    TurretedShooter.seeking = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}