package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    
    double kIntakeSpeed, kFeederSpeed;

    public IntakeCommand(double intakeSpeed, double feedSpeed){
        kIntakeSpeed = intakeSpeed;
        kFeederSpeed = feedSpeed;
    }
  
    @Override
    public void initialize() {
        
    }
  
    @Override
    public void execute() {
        IntakeSubsystem.setIntakeSpeed(kIntakeSpeed);
        FeederSubsystem.setFeederSpeed(kFeederSpeed);
    }
  
    @Override
    public void end(boolean interrupted) {
        IntakeSubsystem.stop();
        FeederSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
  }
