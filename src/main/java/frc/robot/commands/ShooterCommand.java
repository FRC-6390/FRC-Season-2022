package frc.robot.commands;

import frc.robot.Constants.SHOOTER;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Leds;
import frc.robot.subsystems.TurretedShooter;
import frc.robot.subsystems.Leds.LED_COLOURS;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {

  private double shooterTimeout, velocity;

  public ShooterCommand(double velocity) {
    this.velocity = velocity;
  }

  @Override
  public void initialize() {
    shooterTimeout = System.currentTimeMillis() + (1.6  *1000);
    TurretedShooter.seeking = false;

  }

  double speed = 0;

  @Override
  public void execute() {
    // double speed = SHOOTER.SHOOTER_PID.calc(TurretedShooter.shooterEncoder.getVelocity(), -velocity);
    if(TurretedShooter.shooterEncoder.getVelocity() > -velocity) {
      speed -= 0.01;
    }else{
      speed += 0.001;
    }
    TurretedShooter.shooterRight.set(speed);
    TurretedShooter.shooterLeft.set(-speed);
    //System.out.println(TurretedShooter.shooterEncoder.getVelocity() + " " + speed);
    if(shooterTimeout < System.currentTimeMillis() ){
        TurretedShooter.preLeftShooter.set(1);
        TurretedShooter.preRightShooter.set(-1);

        Feeder.setMotorSpeed(0.7);
    }else{
      TurretedShooter.preLeftShooter.set(0.0);
      TurretedShooter.preRightShooter.set(0.0);
    }

  }

  @Override
  public void end(boolean interrupted) {
    TurretedShooter.shooterRight.set(0.0);
    TurretedShooter.shooterLeft.set(0.0);

    TurretedShooter.preLeftShooter.set(0.0);
    TurretedShooter.preRightShooter.set(0.0);

    Feeder.setMotorSpeed(0.0);
    TurretedShooter.seeking = true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}