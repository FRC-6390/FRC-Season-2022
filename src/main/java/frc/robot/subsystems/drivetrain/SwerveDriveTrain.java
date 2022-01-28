package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk3ModuleConfiguration;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SWERVE;
import frc.robot.Constants.SENSOR;
import frc.robot.subsystems.drivetrain.swerve.SwerveModule;
import frc.robot.utils.Units.units;

public class SwerveDriveTrain extends SubsystemBase {

  public com.swervedrivespecialties.swervelib.SwerveModule frontLeft, frontRight, backLeft, backRight;
  public AHRS gyro;
  public SwerveDriveOdometry odometry;
  public SwerveDriveKinematics kinematics;
  public ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0,0);
  public Pose2d pose;

  public SwerveDriveTrain() {
    frontLeft = Mk3SwerveModuleHelper.createFalcon500(Mk3SwerveModuleHelper.GearRatio.STANDARD, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset)
    // frontLeft = new SwerveModule(SWERVE.FRONT_LEFT);
    // frontRight = new SwerveModule(SWERVE.FRONT_RIGHT);
    // backLeft = new SwerveModule(SWERVE.BACK_LEFT);
    // backRight = new SwerveModule(SWERVE.BACK_RIGHT);

    gyro = new AHRS(SENSOR.GYRO_PORT);

    kinematics = new SwerveDriveKinematics(
      new Translation2d(SWERVE.LOCATION_FROM_CENTER.get(units.METERS), SWERVE.LOCATION_FROM_CENTER.get(units.METERS)),
      new Translation2d(SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -SWERVE.LOCATION_FROM_CENTER.get(units.METERS)),
      new Translation2d(-SWERVE.LOCATION_FROM_CENTER.get(units.METERS), SWERVE.LOCATION_FROM_CENTER.get(units.METERS)),
      new Translation2d(-SWERVE.LOCATION_FROM_CENTER.get(units.METERS), -SWERVE.LOCATION_FROM_CENTER.get(units.METERS)));
    
    odometry = new SwerveDriveOdometry(kinematics, gyro.getRotation2d());

    pose = new Pose2d(0, 0, gyro.getRotation2d());
  }

  public void drive(double x, double y, double r, boolean f){
    chassisSpeeds = f ? ChassisSpeeds.fromFieldRelativeSpeeds(x, y, r, gyro.getRotation2d()) : new ChassisSpeeds(x, y, r);
  }

  public void drive(DesiredPosition pos){

  }

  public void reset(){
    resetEncoders();
    resetPose(true);
  }

  public void resetGyro(){
    gyro.zeroYaw();
  }

  public void resetPose(boolean r){
    if(r) resetGyro();
    odometry.resetPosition(new Pose2d(0,0, gyro.getRotation2d()), gyro.getRotation2d());
  }

  public void resetEncoders(){
    for (int i = 0; i < SwerveModule.modules.size(); i++) SwerveModule.modules.get(i).getModuleEncoder().setPosition(0.0);
  }

  private NeutralMode previousState = NeutralMode.Brake;
  public void switchMotorMode(){
    previousState = previousState.equals(NeutralMode.Coast) ? NeutralMode.Brake : NeutralMode.Coast;
    setMotorMode(previousState);
  }

  public void setMotorMode(NeutralMode mode){
    previousState = mode;
    for (int i = 0; i < SwerveModule.modules.size(); i++) {
      SwerveModule.modules.get(i).getDriveMotor().setNeutralMode(mode);
      SwerveModule.modules.get(i).getRotationMotor().setNeutralMode(mode);
    }
  }
 
  @Override
  public void periodic() {
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_SPEED.get(units.METERS));
    for (int i = 0; i < states.length; i++) SwerveModule.modules.get(i).setDesiredState(states[i]);

    pose = odometry.update(gyro.getRotation2d(), SwerveModule.getAllStates());
  }
}