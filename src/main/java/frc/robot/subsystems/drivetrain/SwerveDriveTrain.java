// package frc.robot.subsystems.drivetrain;

// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.sensors.CANCoder;
// import com.kauailabs.navx.frc.AHRS;

// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Translation2d;
// import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
// import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
// import edu.wpi.first.math.kinematics.SwerveModuleState;
// import edu.wpi.first.wpilibj.PowerDistribution;
// import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import frc.robot.subsystems.drivetrain.DesiredPosition.DesiredSpeeds;
// import frc.robot.utils.Units.units;

// public class SwerveDriveTrain extends SubsystemBase {

//   private static TalonFX frontLeftDriveMotor,frontRightDriveMotor,backLeftDriveMotor,backRightDriveMotor, driveMotorArray[];
//   private static TalonFX frontLeftRotationMotor,frontRightRotationMotor,backLeftRotationMotor,backRightRotationMotor, rotationMotorArray[];
//   private static CANCoder frontLeftModuleEncoder, frontRightModuleEncoder, backLeftModuleEncoder, backRightModuleEncoder, moduleEncoderArray[];
//   private static Translation2d frontLeftSwerveLocation, frontRightSwerveLocation, backLeftSwerveLocation, backRightSwerveLocation;
//   private static SwerveModule frontLeftSwerveModule, frontRightSwerveModule, backLeftSwerveModule, backRightSwerveModule, swerveModuleArray[];
//   private static SwerveDriveKinematics swerveDriveKinematics;
//   private static SwerveDriveOdometry swerveDriveOdometry;
//   private static Pose2d robotPosition;
//   private static AHRS gyro;
//   private static PowerDistribution powerDistributionPanel;

//   static {
//     frontLeftDriveMotor = new TalonFX(Constants.MOTOR.FRONT_LEFT_DRIVE);
//     frontRightDriveMotor = new TalonFX(Constants.MOTOR.FRONT_RIGHT_DRIVE);
//     backLeftDriveMotor = new TalonFX(Constants.MOTOR.BACK_LEFT_DRIVE);
//     backRightDriveMotor = new TalonFX(Constants.MOTOR.BACK_RIGHT_DRIVE);

//     frontLeftRotationMotor = new TalonFX(Constants.MOTOR.FRONT_LEFT_ROTATION);
//     frontRightRotationMotor = new TalonFX(Constants.MOTOR.FRONT_RIGHT_ROTATION);
//     backLeftRotationMotor = new TalonFX(Constants.MOTOR.BACK_LEFT_ROTATION);
//     backRightRotationMotor = new TalonFX(Constants.MOTOR.BACK_RIGHT_ROTATION);

//     frontLeftModuleEncoder = new CANCoder(Constants.SENSOR.FRONT_LEFT_ENCODER.getEncoderId());
//     frontRightModuleEncoder = new CANCoder(Constants.SENSOR.FRONT_RIGHT_ENCODER.getEncoderId());
//     backLeftModuleEncoder = new CANCoder(Constants.SENSOR.BACK_LEFT_ENCODER.getEncoderId());
//     backRightModuleEncoder = new CANCoder(Constants.SENSOR.BACK_RIGHT_ENCODER.getEncoderId());

//     frontLeftSwerveLocation = new Translation2d(Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
//     frontRightSwerveLocation = new Translation2d(Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
//     backLeftSwerveLocation = new Translation2d(-Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
//     backRightSwerveLocation = new Translation2d(-Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
 
//     frontLeftSwerveModule = new SwerveModule(frontLeftDriveMotor, frontLeftRotationMotor, frontLeftModuleEncoder, Constants.SWERVE.FRONT_LEFT_OFFSET);
//     frontRightSwerveModule = new SwerveModule(frontRightDriveMotor, frontRightRotationMotor, frontRightModuleEncoder, Constants.SWERVE.FRONT_RIGHT_OFFSET);
//     backLeftSwerveModule = new SwerveModule(backLeftDriveMotor, backLeftRotationMotor,backLeftModuleEncoder, Constants.SWERVE.BACK_LEFT_OFFSET);
//     backRightSwerveModule = new SwerveModule(backRightDriveMotor, backRightRotationMotor, backRightModuleEncoder, Constants.SWERVE.BACK_RIGHT_OFFSET);
  
//     swerveDriveKinematics = new SwerveDriveKinematics(frontLeftSwerveLocation, frontRightSwerveLocation, backLeftSwerveLocation, backRightSwerveLocation);
//     gyro = new AHRS(Constants.SENSOR.GYRO_PORT);
//     robotPosition = new Pose2d(0,0,new Rotation2d());
//     swerveDriveOdometry = new SwerveDriveOdometry(swerveDriveKinematics, gyro.getRotation2d(), robotPosition);

//     powerDistributionPanel = new PowerDistribution(0, ModuleType.kCTRE);

//     driveMotorArray = new TalonFX[] {frontLeftDriveMotor,frontRightDriveMotor,backLeftDriveMotor,backRightDriveMotor};
//     rotationMotorArray = new TalonFX[] {frontLeftRotationMotor,frontRightRotationMotor,backLeftRotationMotor,backRightRotationMotor};
//     moduleEncoderArray = new CANCoder[] {frontLeftModuleEncoder, frontRightModuleEncoder, backLeftModuleEncoder, backRightModuleEncoder};
//     swerveModuleArray = new SwerveModule[] {frontLeftSwerveModule, frontRightSwerveModule, backLeftSwerveModule, backRightSwerveModule};
//   }

//   public SwerveDriveTrain() {
    
//   }

//   public static void drive(double xSpeed, double ySpeed, double rotationSpeed, boolean fieldOrientated){
//     SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(fieldOrientated ? ChassisSpeeds.fromFieldRelativeSpeeds(-xSpeed, -ySpeed, -rotationSpeed, gyro.getRotation2d()) : new ChassisSpeeds(xSpeed, ySpeed, rotationSpeed));
//     SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SWERVE.MAX_SPEED.get(units.METERS));
//     for (int i = 0; i < swerveModuleStates.length; i++) swerveModuleArray[i].setDesiredState(swerveModuleStates[i]);
//   }

//   public static void driveToDesiredPosition(DesiredPosition desiredPosition){
//     DesiredSpeeds speeds = desiredPosition.getDesiredSpeeds(robotPosition);
//     SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(speeds.x, speeds.y, speeds.theta,gyro.getRotation2d()));
//     SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SWERVE.MAX_SPEED.get(units.METERS));
//     for (int i = 0; i < swerveModuleStates.length; i++) swerveModuleArray[i].setDesiredState(swerveModuleStates[i]);
//   }

//   public static void stopAllDrive(){
//     SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(0, 0, 0, gyro.getRotation2d()));
//     SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SWERVE.MAX_SPEED.get(units.METERS));
//     for (int i = 0; i < swerveModuleStates.length; i++) swerveModuleArray[i].setDesiredState(swerveModuleStates[i]);
//   }

//   public static void resetAll() {
//     resetRobotPosition();
//     resetGyro();
//     resetModuleEncoders();
//     setMotorMode(NeutralMode.Brake);
//   }

//   public static void resetGyro(){
//     gyro.reset();
//     swerveDriveOdometry.resetPosition(robotPosition, gyro.getRotation2d());
//   }

//   public static void resetRobotPosition(){
//     System.out.println("pos");
//     robotPosition = new Pose2d(0.0, 0.0, new Rotation2d(0.0));
//     swerveDriveOdometry.resetPosition(robotPosition, gyro.getRotation2d());
//   }

//   public static void resetModuleEncoders(){
//     for (int i = 0; i < moduleEncoderArray.length; i++) moduleEncoderArray[i].setPosition(0.0, 0);
//   }

//   private static NeutralMode mode = NeutralMode.Brake;
//   public static void switchMotorMode(){
//     mode = mode.equals(NeutralMode.Brake) ? NeutralMode.Coast : NeutralMode.Brake;
//     for (int i = 0; i < driveMotorArray.length; i++) {
//       driveMotorArray[i].setNeutralMode(mode);
//       rotationMotorArray[i].setNeutralMode(mode);
//     }
//   }

//   public static void setMotorMode(NeutralMode newMode){
//     mode = newMode;
//     for (int i = 0; i < driveMotorArray.length; i++) {
//       driveMotorArray[i].setNeutralMode(mode);
//       rotationMotorArray[i].setNeutralMode(mode);
//     }
//   }

//   public static double getGyro(){
//    return gyro.getRotation2d().getDegrees();                                        //returns values but does not return to 0 keeps going past 360
//     // return swerveDriveOdometry.getPoseMeters().getRotation().getDegrees();           does not reutrn anything 0.000000000
//     // return swerveModuleArray[1].getAngle().getDegrees();                             return wierd values not sure if its right
//     //return swerveDriveOdometry.getPoseMeters().getY();
//   }

//   @Override
//   public void periodic() {
//     robotPosition = swerveDriveOdometry.update(gyro.getRotation2d(), frontLeftSwerveModule.getState(), frontRightSwerveModule.getState(),
//     backLeftSwerveModule.getState(), backRightSwerveModule.getState());
//     SmartDashboard.putNumber("Robot Position (X) --------", swerveDriveOdometry.getPoseMeters().getX());
//     SmartDashboard.putNumber("Robot Position (Y) ---------", swerveDriveOdometry.getPoseMeters().getY());
//     SmartDashboard.putNumber("Robot Position (Angle) ---------", swerveDriveOdometry.getPoseMeters().getRotation().getDegrees());
//     SmartDashboard.putNumber("Gyro: -------",swerveDriveOdometry.getPoseMeters().getRotation().getDegrees());
//   }
// }




package frc.robot.subsystems.drivetrain;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;
import frc.robot.Constants.SWERVE;;

public class SwerveDriveTrain extends SubsystemBase {

  private SwerveDriveKinematics kinematics;
  private SwerveDriveOdometry odometry;
  private SwerveModule[] swerveModules = new SwerveModule[4];
  private AHRS gyro;
  private ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0,0);
  private double startingX, startingY;
  private Pose2d pose;
  private ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

  public SwerveDriveTrain(double startingX, double startingY) {
    this.startingX = startingX;
    this.startingY = startingY;
    gyro = new AHRS(ROBOT.GYRO_PORT); 
    
    swerveModules[0] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_LEFT_DRIVE, SWERVE.FRONT_LEFT_ROTATION, SWERVE.FRONT_LEFT_ENCODER, SWERVE.FRONT_LEFT_OFFSET);
    swerveModules[1] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_RIGHT_DRIVE, SWERVE.FRONT_RIGHT_ROTATION, SWERVE.FRONT_RIGHT_ENCODER, SWERVE.FRONT_RIGHT_OFFSET);
    swerveModules[2] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_LEFT_DRIVE, SWERVE.BACK_LEFT_ROTATION, SWERVE.BACK_LEFT_ENCODER, SWERVE.BACK_LEFT_OFFSET);
    swerveModules[3] = Mk3SwerveModuleHelper.createFalcon500(tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6, 0),Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_RIGHT_DRIVE, SWERVE.BACK_RIGHT_ROTATION, SWERVE.BACK_RIGHT_ENCODER, SWERVE.BACK_RIGHT_OFFSET);

    kinematics = new SwerveDriveKinematics(SWERVE.SWERVE_LOCATIONS);
    odometry = new SwerveDriveOdometry(kinematics, gyro.getRotation2d());
    pose = new Pose2d(startingX,startingY,gyro.getRotation2d());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot X", ()->pose.getX());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Y", ()->pose.getY());
    tab.getLayout("Odometry", BuiltInLayouts.kList).addNumber("Robot Rotation",()-> pose.getRotation().getDegrees());
  

  }

  public void reset(boolean zeroGyro){
    System.out.println("reset");
    if(zeroGyro) zero();
    odometry.resetPosition(new Pose2d(startingX,startingY,gyro.getRotation2d()), gyro.getRotation2d());   
  }

  public void zero(){
    gyro.zeroYaw();
  }

  public AHRS getGyro(){
    return gyro;
  }
  
  public Pose2d pos(){
    return pose;
  }

  public Rotation2d rotation(){
    if (gyro.isMagnetometerCalibrated()) {
      return Rotation2d.fromDegrees(-gyro.getFusedHeading());
    }
    return Rotation2d.fromDegrees(-gyro.getYaw());
  }

  public void drive(ChassisSpeeds speeds){
    chassisSpeeds = speeds;
  }

  @Override
  public void periodic() {
    
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_VELCOCITY);
    pose = odometry.update(gyro.getRotation2d(), states);
    for (int i = 0; i < states.length; i++) swerveModules[i].set(states[i].speedMetersPerSecond/SWERVE.MAX_VELCOCITY*SWERVE.MAX_VOLTAGE, states[i].angle.getRadians());
   }

}