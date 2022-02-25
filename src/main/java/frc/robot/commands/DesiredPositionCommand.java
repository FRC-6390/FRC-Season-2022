package frc.robot.commands;

import java.util.Arrays;
import java.util.Iterator;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;

public class DesiredPositionCommand extends CommandBase {

    private DriveTrain driveTrain;
    private DesiredPosition currentCord;
    private boolean done;
    private ShuffleboardLayout waypointLayout = Shuffleboard.getTab("Auto").getLayout("Waypoint", BuiltInLayouts.kList).withSize(2, 8).withPosition(0, 0);
    private ShuffleboardLayout drivelayout = Shuffleboard.getTab("Auto").getLayout("Drive PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(2, 0);
    private ShuffleboardLayout rotationlayout = Shuffleboard.getTab("Auto").getLayout("Rotation PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(4, 0);
    private static boolean shuffleboard = true;
    private Iterator<DesiredPosition> cords;

    public DesiredPositionCommand(DriveTrain subsystem, DesiredPosition... cords) {
      this(subsystem,Arrays.asList(cords).iterator());
    }

    public DesiredPositionCommand(DriveTrain subsystem, Iterator<DesiredPosition> cords) {
      driveTrain = subsystem;
      this.cords = cords;
    }

    //outputs all data un FRC Shuffleboard
    private void setupShuffleboard(){
      waypointLayout.addNumber("X", () -> currentCord.getPos().getX());
      waypointLayout.addNumber("Y", () -> currentCord.getPos().getY());
      waypointLayout.addNumber("Theta", () -> currentCord.getPos().getX());
      waypointLayout.addBoolean("At Threshold", () -> currentCord.threashhold());
      drivelayout.addNumber("X Error", () -> currentCord.getXPID().getError());
      drivelayout.addNumber("Y Error", () -> currentCord.getYPID().getError());
      rotationlayout.addNumber("R Error", () -> currentCord.getRotationPID().getError());
      shuffleboard = false;
    }
  
    @Override
    public void initialize() {
      currentCord = cords.next();
      done = false;
      if(shuffleboard)setupShuffleboard();
    }
  
    @Override
    public void execute() { 
        //moves onto the next position when it has reached the minimum threshold of the current waypoint 
        if(!currentCord.threashhold()){
          driveTrain.drive(currentCord.getChassisSpeeds(driveTrain.getPos()));
        }else{
          if (!cords.hasNext()) done = true;
          else currentCord = cords.next();
        }
    }
  
    @Override
    public void end(boolean interrupted) {}
  
    @Override
    public boolean isFinished() {
      return done;
    }
  }