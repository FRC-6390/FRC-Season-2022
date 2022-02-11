// package frc.robot.commands;

// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;
// import frc.robot.utils.json.JsonManager;
// import frc.robot.subsystems.DesiredPosition;
// import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.DesiredPosition;

// public class PointToPoint extends CommandBase {

//   public DriveTrain drivetrain;
//   public RobotContainer robotContainer;
//   public JsonManager jsonManager;
//   private List<DesiredPosition> desiredList;
//   private Iterator<DesiredPosition> desiredIterator;
//   private DesiredPosition desiredPosition;
//   private boolean done;

//   public PointToPoint() {}

//   @Override
//   public void initialize() {
//     done = false;
//     robotContainer = new RobotContainer();
//     jsonManager = new JsonManager();

//     String autoSelected = SmartDashboard.getString("Auto Selector","auto1");
//     System.out.println("AUTO_________________: " + autoSelected);

//     try {
//       System.out.println("READING AUTO FILE");
//       jsonManager.readJson(autoSelected);
//     } catch (Exception e) {
//       e.printStackTrace();
//     }

//     //iterate over json here 
//     desiredList = new ArrayList<>();
//     for(int z = 0; z < jsonManager.posList.size(); z++){
//       desiredList.add(desiredPosition(jsonManager.xList.get(z), jsonManager.yList.get(z), jsonManager.thetaList.get(z)));
//     }
//     System.out.println(desiredList);

//     desiredIterator = desiredList.iterator();
//     desiredPosition = desiredIterator.next();
//   }


//   @Override
//   public void execute() {
//     if(!desiredPosition.threashhold()){
//         drivetrain.drive(desiredPosition.getChassisSpeeds(drivetrain));
//       }else{
//         if (!desiredIterator.hasNext()){
//           done = true;
//           //auto ends
//           System.out.println("AUTO COMPLETE");
//         }
//         else desiredPosition = desiredIterator.next();
//       }
//   }

//   @Override
//   public void end(boolean interrupted) {}

//   @Override
//   public boolean isFinished() {
//     return done;
//   }

//   // for testing purposes in the future
//   // public static void main(String[] args) throws Exception {
//   //   PointAtoB point = new PointAtoB();
//   //   point.initialize();
//   // }
 
// }