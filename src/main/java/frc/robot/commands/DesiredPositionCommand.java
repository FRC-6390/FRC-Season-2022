package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;

public class DesiredPositionCommand extends CommandBase {

    private DriveTrain driveTrain;
    private DesiredPosition cords[];
    private int i = 0;
    private DesiredPosition currentCord;
    private boolean done;
    private ShuffleboardLayout waypointLayout = Shuffleboard.getTab("Auto").getLayout("Waypoint", BuiltInLayouts.kList).withSize(2, 8).withPosition(0, 0);
    private ShuffleboardLayout drivelayout = Shuffleboard.getTab("Auto").getLayout("Drive PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(2, 0);
    private ShuffleboardLayout rotationlayout = Shuffleboard.getTab("Auto").getLayout("Rotation PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(4, 0);
    private static boolean shuffleboard = true;

    public DesiredPositionCommand(DriveTrain subsystem, DesiredPosition... cords) {
      driveTrain = subsystem;
      this.cords = cords;
      
    }

    private void setupShuffleboard(){
      waypointLayout.addNumber("Id", () -> i);
      waypointLayout.addNumber("X", () -> currentCord.x());
      waypointLayout.addNumber("Y", () -> currentCord.y());
      waypointLayout.addNumber("Theta", () -> currentCord.theta());
      waypointLayout.addBoolean("At Threshold", () -> currentCord.threashhold());
      drivelayout.addNumber("P", () -> currentCord.getDrivePID().getP());
      drivelayout.addNumber("I", () -> currentCord.getDrivePID().getI());
      drivelayout.addNumber("D", () -> currentCord.getDrivePID().getD());
      drivelayout.addNumber("Setpoint", () -> currentCord.getDrivePID().getSetpoint());
      rotationlayout.addNumber("P", () -> currentCord.getRotationPID().getP());
      rotationlayout.addNumber("I", () -> currentCord.getRotationPID().getI());
      rotationlayout.addNumber("D", () -> currentCord.getRotationPID().getD());
      rotationlayout.addNumber("Setpoint", () -> currentCord.getRotationPID().getSetpoint());
      shuffleboard = false;
    }
  
    @Override
    public void initialize() {
      currentCord = cords[i];
      done = false;
      // if(shuffleboard)setupShuffleboard();
    }
  
    @Override
    public void execute() {    
        if(!cords[i].threashhold()){
            driveTrain.drive(cords[i].getChassisSpeeds(driveTrain.pos()));
        }else{
          if(i > cords.length-1) done = true;
          //else i++;
        }
    }
  
    @Override
    public void end(boolean interrupted) {}
  
    @Override
    public boolean isFinished() {
      return done;
    }
  }
  