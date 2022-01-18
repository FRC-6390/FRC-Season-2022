package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.DesiredPosition.DesiredSpeeds;
import frc.robot.utils.Units.units;

public class SwerveDriveTrain extends SubsystemBase {

  private static TalonFX frontLeftDriveMotor,frontRightDriveMotor,backLeftDriveMotor,backRightDriveMotor, driveMotorArray[];
  private static TalonFX frontLeftRotationMotor,frontRightRotationMotor,backLeftRotationMotor,backRightRotationMotor, rotationMotorArray[];
  private static CANCoder frontLeftModuleEncoder, frontRightModuleEncoder, backLeftModuleEncoder, backRightModuleEncoder, moduleEncoderArray[];
  private static Translation2d frontLeftSwerveLocation, frontRightSwerveLocation, backLeftSwerveLocation, backRightSwerveLocation;
  private static SwerveModule frontLeftSwerveModule, frontRightSwerveModule, backLeftSwerveModule, backRightSwerveModule, swerveModuleArray[];
  private static SwerveDriveKinematics swerveDriveKinematics;
  private static SwerveDriveOdometry swerveDriveOdometry;
  private static Pose2d robotPosition;
  private static AHRS gyro;
  private static PowerDistribution powerDistributionPanel;

  static {
    frontLeftDriveMotor = new TalonFX(Constants.MOTOR.FRONT_LEFT_DRIVE);
    frontRightDriveMotor = new TalonFX(Constants.MOTOR.FRONT_RIGHT_DRIVE);
    backLeftDriveMotor = new TalonFX(Constants.MOTOR.BACK_LEFT_DRIVE);
    backRightDriveMotor = new TalonFX(Constants.MOTOR.BACK_RIGHT_DRIVE);

    frontLeftRotationMotor = new TalonFX(Constants.MOTOR.FRONT_LEFT_ROTATION);
    frontRightRotationMotor = new TalonFX(Constants.MOTOR.FRONT_RIGHT_ROTATION);
    backLeftRotationMotor = new TalonFX(Constants.MOTOR.BACK_LEFT_ROTATION);
    backRightRotationMotor = new TalonFX(Constants.MOTOR.BACK_RIGHT_ROTATION);

    frontLeftModuleEncoder = new CANCoder(Constants.SENSOR.FRONT_LEFT_ENCODER.getEncoderId());
    frontRightModuleEncoder = new CANCoder(Constants.SENSOR.FRONT_RIGHT_ENCODER.getEncoderId());
    backLeftModuleEncoder = new CANCoder(Constants.SENSOR.BACK_LEFT_ENCODER.getEncoderId());
    backRightModuleEncoder = new CANCoder(Constants.SENSOR.BACK_RIGHT_ENCODER.getEncoderId());

    frontLeftSwerveLocation = new Translation2d(Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
    frontRightSwerveLocation = new Translation2d(Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
    backLeftSwerveLocation = new Translation2d(-Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
    backRightSwerveLocation = new Translation2d(-Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -Constants.SWERVE.LOCATION_FROM_CENTER.get(units.METERS));
 
    frontLeftSwerveModule = new SwerveModule(frontLeftDriveMotor, frontLeftRotationMotor, frontLeftModuleEncoder, Constants.SWERVE.FRONT_LEFT_OFFSET);
    frontRightSwerveModule = new SwerveModule(frontRightDriveMotor, frontRightRotationMotor, frontRightModuleEncoder, Constants.SWERVE.FRONT_RIGHT_OFFSET);
    backLeftSwerveModule = new SwerveModule(backLeftDriveMotor, backLeftRotationMotor,backLeftModuleEncoder, Constants.SWERVE.BACK_LEFT_OFFSET);
    backRightSwerveModule = new SwerveModule(backRightDriveMotor, backRightRotationMotor, backRightModuleEncoder, Constants.SWERVE.BACK_RIGHT_OFFSET);
  
    swerveDriveKinematics = new SwerveDriveKinematics(frontLeftSwerveLocation, frontRightSwerveLocation, backLeftSwerveLocation, backRightSwerveLocation);
    gyro = new AHRS(Constants.SENSOR.GYRO_PORT);
    robotPosition = new Pose2d(0,0,new Rotation2d());
    swerveDriveOdometry = new SwerveDriveOdometry(swerveDriveKinematics, gyro.getRotation2d(), robotPosition);

    powerDistributionPanel = new PowerDistribution(0, ModuleType.kCTRE);

    driveMotorArray = new TalonFX[] {frontLeftDriveMotor,frontRightDriveMotor,backLeftDriveMotor,backRightDriveMotor};
    rotationMotorArray = new TalonFX[] {frontLeftRotationMotor,frontRightRotationMotor,backLeftRotationMotor,backRightRotationMotor};
    moduleEncoderArray = new CANCoder[] {frontLeftModuleEncoder, frontRightModuleEncoder, backLeftModuleEncoder, backRightModuleEncoder};
    swerveModuleArray = new SwerveModule[] {frontLeftSwerveModule, frontRightSwerveModule, backLeftSwerveModule, backRightSwerveModule};
  }

  public SwerveDriveTrain() {
    
  }
  
  public static void resetAll() {
    resetRobotPosition(true);
    resetGyro();
    resetModuleEncoders();
    setMotorMode(NeutralMode.Brake);
  }

  public static void drive(double xSpeed, double ySpeed, double rotationSpeed, boolean fieldOrientated){
    SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(fieldOrientated ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotationSpeed, gyro.getRotation2d()) : new ChassisSpeeds(xSpeed, ySpeed, rotationSpeed));
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SWERVE.MAX_SPEED.get(units.METERS));
    for (int i = 0; i < swerveModuleStates.length; i++) swerveModuleArray[i].setDesiredState(swerveModuleStates[i]);
  }

  public static void driveToDesiredPosition(DesiredPosition desiredPosition){
    DesiredSpeeds speeds = desiredPosition.getDesiredSpeeds(robotPosition);
    SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(speeds.x,speeds.y,speeds.theta,gyro.getRotation2d()));
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SWERVE.MAX_SPEED.get(units.METERS));
    for (int i = 0; i < swerveModuleStates.length; i++) swerveModuleArray[i].setDesiredState(swerveModuleStates[i]);
  }

  public static void resetGyro(){
    gyro.reset();
  }

  public static void resetRobotPosition(boolean resetRotation){
    robotPosition = new Pose2d(0, 0, resetRotation ? new Rotation2d(0) : gyro.getRotation2d());
  }

  public static void resetModuleEncoders(){
    for (int i = 0; i < moduleEncoderArray.length; i++) moduleEncoderArray[i].setPosition(0.0, 0);
  }

  private static NeutralMode mode = NeutralMode.Brake;
  public static void switchMotorMode(){
    mode = mode.equals(NeutralMode.Brake) ? NeutralMode.Coast : NeutralMode.Brake;
    for (int i = 0; i < driveMotorArray.length; i++) {
      driveMotorArray[i].setNeutralMode(mode);
      rotationMotorArray[i].setNeutralMode(mode);
    }
  }

  public static void setMotorMode(NeutralMode newMode){
    mode = newMode;
    for (int i = 0; i < driveMotorArray.length; i++) {
      driveMotorArray[i].setNeutralMode(mode);
      rotationMotorArray[i].setNeutralMode(mode);
    }
  }

  @Override
  public void periodic() {
      System.out.println("Gyro: " + gyro.getRotation2d().getDegrees());
      SmartDashboard.putNumber("Robot Position (Angle)", swerveDriveOdometry.getPoseMeters().getRotation().getDegrees());
  }
}
