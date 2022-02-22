package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PID;
import frc.robot.Constants.ROBOT;
import frc.robot.Constants.SWERVE;

public class DriveTrain extends SubsystemBase {

  private SwerveDriveKinematics kinematics;
  private SwerveDriveOdometry odometry;
  private SwerveModule[] swerveModules = new SwerveModule[4];
  private PigeonIMU gyro;
  private ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0,0);
  private double startingX, startingY;
  private Pose2d pose;
  private frc.robot.utils.PID driftCorrectionPID = new frc.robot.utils.PID(0.02, 0.00, 0.0);
  private double desiredHeading;
  private ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

  public DriveTrain(double startingX, double startingY) {
    this.startingX = startingX;
    this.startingY = startingY;
    gyro = new PigeonIMU(ROBOT.GYRO_PORT); 

    swerveModules[0] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_LEFT_DRIVE, SWERVE.FRONT_LEFT_ROTATION, SWERVE.FRONT_LEFT_ENCODER, SWERVE.FRONT_LEFT_OFFSET);
    swerveModules[1] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_RIGHT_DRIVE, SWERVE.FRONT_RIGHT_ROTATION, SWERVE.FRONT_RIGHT_ENCODER, SWERVE.FRONT_RIGHT_OFFSET);
    swerveModules[2] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_LEFT_DRIVE, SWERVE.BACK_LEFT_ROTATION, SWERVE.BACK_LEFT_ENCODER, SWERVE.BACK_LEFT_OFFSET);
    swerveModules[3] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_RIGHT_DRIVE, SWERVE.BACK_RIGHT_ROTATION, SWERVE.BACK_RIGHT_ENCODER, SWERVE.BACK_RIGHT_OFFSET  );

    kinematics = new SwerveDriveKinematics(SWERVE.SWERVE_LOCATIONS);
    odometry = new SwerveDriveOdometry(kinematics, rotation());
    pose = new Pose2d(startingX,startingY, rotation());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot X", ()->x());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Y", ()->y());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Rotation",()->rotation().getDegrees());
    desiredHeading = pose.getRotation().getDegrees();
  }

  public void reset(boolean zeroGyro){
    System.out.println("reset");
    if(zeroGyro) zero();
    odometry.resetPosition(new Pose2d(startingX,startingY,rotation()),rotation());   
  }

  public void zero(){
    gyro.setFusedHeading(0.0);
    desiredHeading = 0;
  }

  public PigeonIMU getGyro(){
    return gyro;
  }
  
  public double x(){
    return pose.getX();
  }

  public double y(){
    return pose.getY();
  }

  public Rotation2d rotation(){
    return Rotation2d.fromDegrees(gyro.getFusedHeading());
  }

  public void drive(ChassisSpeeds speeds){
    chassisSpeeds = speeds;
  }

  @Override
  public void periodic() {
    if(Math.abs(chassisSpeeds.omegaRadiansPerSecond) > 0 ){ //add velocity to fix mushy feeling
      desiredHeading = pose.getRotation().getDegrees();
    }else{      
      chassisSpeeds.omegaRadiansPerSecond += driftCorrectionPID.calculate(pose.getRotation().getDegrees(), desiredHeading);
    }
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_VELCOCITY);
    for (int i = 0; i < states.length; i++) swerveModules[i].set(states[i].speedMetersPerSecond/SWERVE.MAX_VELCOCITY*SWERVE.MAX_VOLTAGE, states[i].angle.getRadians());
    pose = odometry.update(rotation(), states);    
  }

  // public void updateOffsets(int a,int b,int c, int d){
  //   swerveModules[0] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_LEFT_DRIVE, SWERVE.FRONT_LEFT_ROTATION, SWERVE.FRONT_LEFT_ENCODER, a);
  //   swerveModules[1] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_RIGHT_DRIVE, SWERVE.FRONT_RIGHT_ROTATION, SWERVE.FRONT_RIGHT_ENCODER, b);
  //   swerveModules[2] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_LEFT_DRIVE, SWERVE.BACK_LEFT_ROTATION, SWERVE.BACK_LEFT_ENCODER, c);
  //   swerveModules[3] = Mk4SwerveModuleHelper.createFalcon500(tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6, 0),Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_RIGHT_DRIVE, SWERVE.BACK_RIGHT_ROTATION, SWERVE.BACK_RIGHT_ENCODER, d);
  // }


}


