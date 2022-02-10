package frc.robot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.json.JsonManager;

public class DesiredPositionCommand extends CommandBase {

    private DriveTrain driveTrain;
    private DesiredPosition currentCord;
    private boolean done;
    private ShuffleboardLayout waypointLayout = Shuffleboard.getTab("Auto").getLayout("Waypoint", BuiltInLayouts.kList).withSize(2, 8).withPosition(0, 0);
    private ShuffleboardLayout drivelayout = Shuffleboard.getTab("Auto").getLayout("Drive PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(2, 0);
    private ShuffleboardLayout rotationlayout = Shuffleboard.getTab("Auto").getLayout("Rotation PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(4, 0);
    private static boolean shuffleboard = true;
    private Iterator<DesiredPosition> cords;
    public RobotContainer robotContainer;
    public JsonManager jsonManager;
    private List<DesiredPosition> desiredList;
    private Iterator<DesiredPosition> desiredIterator;

    public DesiredPositionCommand(DriveTrain subsystem) {
      driveTrain = subsystem;
      // this.cords = Arrays.asList(cords).iterator();
    }

    private void setupShuffleboard(){
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
      currentCord = cords.next();
      done = false;
      // if(shuffleboard)setupShuffleboard();

      robotContainer = new RobotContainer();
      jsonManager = new JsonManager();

      String autoSelected = SmartDashboard.getString("Auto Selector","auto1");
      System.out.println("AUTO_________________: " + autoSelected);

      try {
        System.out.println("READING AUTO FILE");
        jsonManager.readJson(autoSelected);
      } catch (Exception e) {
        e.printStackTrace();
      }

      //iterate over json here 
      desiredList = new ArrayList<>();
      for(int z = 0; z < jsonManager.posList.size(); z++){
        // desiredList.add(jsonManager.xList.get(z), jsonManager.yList.get(z), jsonManager.thetaList.get(z));
      }
      System.out.println(desiredList);

      desiredIterator = desiredList.iterator();
      currentCord = desiredIterator.next();
    }
  
    @Override
    public void execute() {  
        if(!currentCord.threashhold()){
          driveTrain.drive(currentCord.getChassisSpeeds(driveTrain));
        }else{
          if (!cords.hasNext()){
            done = true;
            System.out.println("AUTO COMPLETE");
          }
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
  