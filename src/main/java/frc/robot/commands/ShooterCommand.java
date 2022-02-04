package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import frc.robot.subsystems.vission.LimeLightTurretSubsystem;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterCommand extends CommandBase {

  private boolean reverse = false;

  public ShooterCommand(boolean reverse) {
    this.reverse = reverse;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { 
    //not sure if there is an encoder
    if(ShooterSubsystem.getVolocity() < LimeLightTurretSubsystem.getVelocityReomended()){
      ShooterSubsystem.setMotorSpeed(reverse ? -Constants.SHOOTER.HIGH_VELOCITY : Constants.SHOOTER.HIGH_VELOCITY);
    }else{
      ShooterSubsystem.setMotorSpeed(reverse ? -Constants.SHOOTER.HIGH_VELOCITY : Constants.SHOOTER.HIGH_VELOCITY);
      new PreShooterCommand(reverse);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
