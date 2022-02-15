package frc.robot.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.utils.PID;
import frc.robot.utils.json.JsonManager;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DesiredPosition;

public class PointToPoint extends CommandBase {

  public DriveTrain drivetrain;
  public RobotContainer robotContainer;
  public JsonManager jsonManager;
  private List<DesiredPosition> desiredList;
  private Iterator<DesiredPosition> desiredIterator;
  private DesiredPosition desiredPosition;
  private ShuffleboardLayout waypointLayout = Shuffleboard.getTab("Auto").getLayout("Waypoint", BuiltInLayouts.kList).withSize(2, 8).withPosition(0, 0);
  private ShuffleboardLayout drivelayout = Shuffleboard.getTab("Auto").getLayout("Drive PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(2, 0);
  private ShuffleboardLayout rotationlayout = Shuffleboard.getTab("Auto").getLayout("Rotation PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(4, 0);
  
  private boolean done;

  public PointToPoint(DriveTrain subsystem) {
    drivetrain = subsystem;
  }


  private void setupShuffleboard(){
    waypointLayout.addNumber("X", () -> desiredPosition.x());
    waypointLayout.addNumber("Y", () -> desiredPosition.y());
    waypointLayout.addNumber("Theta", () -> desiredPosition.theta());
    waypointLayout.addBoolean("At Threshold", () -> desiredPosition.threashhold());
    drivelayout.addNumber("P", () -> desiredPosition.getXPID().getP());
    drivelayout.addNumber("I", () -> desiredPosition.getXPID().getI());
    drivelayout.addNumber("D", () -> desiredPosition.getXPID().getD());
    drivelayout.addNumber("X Error", () -> desiredPosition.getXPID().getError());
    drivelayout.addNumber("Y Error", () -> desiredPosition.getYPID().getError());
    drivelayout.addNumber("X Setpoint", () -> desiredPosition.getXPID().getSetpoint());
    drivelayout.addNumber("Y Setpoint", () -> desiredPosition.getYPID().getSetpoint());   
    rotationlayout.addNumber("P", () -> desiredPosition.getRotationPID().getP());
    rotationlayout.addNumber("I", () -> desiredPosition.getRotationPID().getI());
    rotationlayout.addNumber("D", () -> desiredPosition.getRotationPID().getD());
    rotationlayout.addNumber("Error", () -> desiredPosition.getRotationPID().getError());
    rotationlayout.addNumber("Setpoint", () -> desiredPosition.getRotationPID().getSetpoint());
    //shuffleboard = false;
  }

  @Override
  public void initialize() {
    done = false;
    jsonManager = new JsonManager();

    String autoSelected = SmartDashboard.getString("Auto Selector","auto1");
    System.out.println("AUTO_________________: " + autoSelected);

    try {
      System.out.println("READING AUTO FILE");
      jsonManager.readJson(autoSelected);
    } catch (Exception e) {
      System.out.println("AUTO ERROR");
      e.printStackTrace();
    }

    //iterate over json here 
    desiredList = new ArrayList<>();
    for(int z = 0; z < jsonManager.posList.size(); z++){
      
      // desiredList.add(new DesiredPosition (jsonManager.xList.get(z), jsonManager.yList.get(z), jsonManager.thetaList.get(z)));

      desiredList.add(new DesiredPosition (new Pose2d(jsonManager.xList.get(z), jsonManager.yList.get(z), Rotation2d.fromDegrees(jsonManager.thetaList.get(z)))));
      
    }
    System.out.println(desiredList);

    desiredIterator = desiredList.iterator();
    desiredPosition = desiredIterator.next();
  }


  @Override
  public void execute() {
    if(!desiredPosition.threashhold()){
      drivetrain.drive(desiredPosition.getChassisSpeeds(drivetrain));
    }else{
      if (!desiredIterator.hasNext()) done = true;
      else desiredPosition = desiredIterator.next();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return done;
  }

  // for testing purposes in the future
  // public static void main(String[] args) throws Exception {
  //   PointAtoB point = new PointAtoB();
  //   point.initialize();
  // }
 
}