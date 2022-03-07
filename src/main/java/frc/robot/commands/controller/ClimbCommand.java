package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbCommand extends CommandBase {
    
    double kSpeed;

    public ClimbCommand(double speed){
        kSpeed = speed;
    }
  
    @Override
    public void initialize() {

    }
  
    @Override
    public void execute() {
        ClimberSubsystem.setElevatorSpeed(kSpeed);
    }
  
    @Override
    public void end(boolean interrupted) {
        ClimberSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
  }
