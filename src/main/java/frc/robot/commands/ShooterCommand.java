package frc.robot.commands;

import frc.robot.Constants.SHOOTER;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import frc.robot.subsystems.TurretedShooter;
import frc.robot.subsystems.Leds.LED_COLOURS;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {

  private double shooterTimeout;

  public ShooterCommand() {
  }

  @Override
  public void initialize() {
    shooterTimeout = System.currentTimeMillis() + (2*1000);
  }

  @Override
  public void execute() {
    TurretedShooter.shooterRight.set(-SHOOTER.SHOOTER_PID.calc(-TurretedShooter.shooterEncoder.getVelocity(), SHOOTER.VELOCITY));
    if(SHOOTER.SHOOTER_PID.threshhold() || shooterTimeout < System.currentTimeMillis() ){
        TurretedShooter.preLeftShooter.set(1);
    }
  }

  @Override
  public void end(boolean interrupted) {
    TurretedShooter.shooterRight.set(0.0);
    TurretedShooter.preLeftShooter.set(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}