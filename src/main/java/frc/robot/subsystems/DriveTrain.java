package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;
import frc.robot.Constants.SWERVE;;

public class DriveTrain extends SubsystemBase {

  private SwerveDriveKinematics kinematics;
  private SwerveDriveOdometry odometry;
  private SwerveModule[] swerveModules = new SwerveModule[4];
  private PigeonIMU gyro;
  private ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0,0);
  private double startingX, startingY;
  private Pose2d pose;
  private ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

  public DriveTrain(double startingX, double startingY) {
    this.startingX = startingX;
    this.startingY = startingY;
    gyro = new PigeonIMU(ROBOT.GYRO_PORT); 

    swerveModules[0] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_LEFT_DRIVE, SWERVE.FRONT_LEFT_ROTATION, SWERVE.FRONT_LEFT_ENCODER, SWERVE.FRONT_LEFT_OFFSET);
    swerveModules[1] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_RIGHT_DRIVE, SWERVE.FRONT_RIGHT_ROTATION, SWERVE.FRONT_RIGHT_ENCODER, SWERVE.FRONT_RIGHT_OFFSET);
    swerveModules[2] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_LEFT_DRIVE, SWERVE.BACK_LEFT_ROTATION, SWERVE.BACK_LEFT_ENCODER, SWERVE.BACK_LEFT_OFFSET);
    swerveModules[3] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_RIGHT_DRIVE, SWERVE.BACK_RIGHT_ROTATION, SWERVE.BACK_RIGHT_ENCODER, SWERVE.BACK_RIGHT_OFFSET);
    
    kinematics = new SwerveDriveKinematics(SWERVE.SWERVE_LOCATIONS);
    odometry = new SwerveDriveOdometry(kinematics, rotation());
    pose = new Pose2d(startingX,startingY, rotation());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot X", ()->pose.getX());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Y", ()->pose.getY());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Rotation",()->pose.getRotation().getDegrees());
  }

  public void reset(boolean zeroGyro){
    System.out.println("reset");
    if(zeroGyro) zero();
    odometry.resetPosition(new Pose2d(startingX,startingY,rotation()),rotation());   
  }

  public void zero(){
    gyro.setFusedHeading(0.0);
  }

  public PigeonIMU getGyro(){
    return gyro;
  }
  
  public Pose2d pos(){
    return pose;
  }

  public Rotation2d rotation(){
    return Rotation2d.fromDegrees(gyro.getFusedHeading());
  }

  public void drive(ChassisSpeeds speeds){
    chassisSpeeds = speeds;
  }

  @Override
  public void periodic() {
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_VELCOCITY);
    for (int i = 0; i < states.length; i++) {
      swerveModules[i].set(states[i].speedMetersPerSecond/SWERVE.MAX_VELCOCITY*SWERVE.MAX_VOLTAGE, states[i].angle.getRadians());
      states[i] = getState(swerveModules[i].getDriveVelocity(), swerveModules[i].getSteerAngle());
    }
    pose = odometry.update(rotation(), states);
  }

  public SwerveModuleState getState(double velocity, double angle) {
    return new SwerveModuleState(nativeUnitsToDistanceMeters(velocity*10), Rotation2d.fromDegrees(angle));
  }

  private double nativeUnitsToDistanceMeters(double sensorCounts){
    double motorRotations = (double)sensorCounts / 4096d;
    double wheelRotations = motorRotations / 8.16;
    double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(2));
    return positionMeters;
  } 

}


