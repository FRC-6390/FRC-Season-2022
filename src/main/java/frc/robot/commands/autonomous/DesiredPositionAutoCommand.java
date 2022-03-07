// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import java.util.Iterator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;
import utilities.desiredlib.DesiredPosition;

public class DesiredPositionAutoCommand extends CommandBase {

  Iterator<DesiredPosition> kPath;
  DesiredPosition kDesiredPosition;
 
  public DesiredPositionAutoCommand(Iterator<DesiredPosition> path){
    kPath = path;
  }

  @Override
  public void initialize() {
    kDesiredPosition = kPath.next();
  }

  @Override
  public void execute() {
      if(!kDesiredPosition.threshold())SwerveDriveSubsystem.drive(kDesiredPosition.getChassisSpeeds(SwerveDriveSubsystem.odometry()));
      else if(kPath.hasNext())kDesiredPosition = kPath.next();
  }

  @Override
  public void end(boolean interrupted) {
    SwerveDriveSubsystem.stop();
  }

  @Override
  public boolean isFinished() {
      return !kPath.hasNext() && kDesiredPosition.threshold();
  }
}
