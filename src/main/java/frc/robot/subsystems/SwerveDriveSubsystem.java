package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;
import frc.robot.Constants.SWERVE;
import swervelib.Mk4SwerveModuleHelper;
import swervelib.SwerveModule;

public class SwerveDriveSubsystem extends SubsystemBase {
  //Sendable
  static SwerveDriveKinematics kDriveKinematics;
  static SwerveDriveOdometry kDriveOdometry;
  static SwerveModule[] kSwerveModules = new SwerveModule[4];
  static PigeonIMU kPigeonIMU;
  static PowerDistribution kPowerDistribution;
  static ChassisSpeeds kChassisSpeeds;
  static Rotation2d kHeading;

  static {
    kDriveKinematics = new SwerveDriveKinematics(SWERVE.SWERVE_LOCATIONS);
    kPigeonIMU = new PigeonIMU(ROBOT.GYRO_PORT);
    kPowerDistribution = new PowerDistribution(ROBOT.PDH_PORT, ModuleType.kRev);
    //tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0, 0)
    kSwerveModules[0] = Mk4SwerveModuleHelper.createFalcon500(Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_LEFT[0], SWERVE.FRONT_LEFT[1], SWERVE.FRONT_LEFT[2], SWERVE.FRONT_LEFT_OFFSET);
    kSwerveModules[1] = Mk4SwerveModuleHelper.createFalcon500(Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.FRONT_Right[0], SWERVE.FRONT_Right[1], SWERVE.FRONT_Right[2], SWERVE.FRONT_RIGHT_OFFSET);
    kSwerveModules[2] = Mk4SwerveModuleHelper.createFalcon500(Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_LEFT[0], SWERVE.BACK_LEFT[1], SWERVE.BACK_LEFT[2], SWERVE.BACK_LEFT_OFFSET);
    kSwerveModules[3] = Mk4SwerveModuleHelper.createFalcon500(Mk4SwerveModuleHelper.GearRatio.L1, SWERVE.BACK_RIGHT[0], SWERVE.BACK_RIGHT[1], SWERVE.BACK_RIGHT[2], SWERVE.BACK_RIGHT_OFFSET);
  
    kDriveOdometry = new SwerveDriveOdometry(kDriveKinematics, rotation());

    SWERVE.DRIFT_CORRECTION_PID.setkCurrentSupplier(()->rotation().getRadians());
    SWERVE.DRIFT_CORRECTION_PID.setkSetPointSupplier(()->heading().getRadians());
  }

  public static void reset(){
    zero();
    kDriveOdometry.resetPosition(new Pose2d(), rotation());
    kPowerDistribution.clearStickyFaults();
  }

  public static Rotation2d rotation(){
    return Rotation2d.fromDegrees(kPigeonIMU.getFusedHeading());
  }

  public static Pose2d odometry(){
    return kDriveOdometry.getPoseMeters();
  }

  public static void zero(){
    kPigeonIMU.setFusedHeading(0.0);
    updateHeading();
  }

  private static void updateHeading(){
    kHeading = rotation();
  }

  public static void drive(ChassisSpeeds speeds){
    kChassisSpeeds = speeds;
    limit();
  }

  public static void stop(){
    kChassisSpeeds = new ChassisSpeeds();
  }

  private static void limit(){
    kChassisSpeeds.omegaRadiansPerSecond *= SWERVE.MAX_SPEED;
    kChassisSpeeds.vxMetersPerSecond *= SWERVE.MAX_SPEED;
    kChassisSpeeds.vyMetersPerSecond *= SWERVE.MAX_SPEED;
  }

  private static Rotation2d heading(){
    return kHeading;
  }

  private static void driftCorrection(){
    if(kChassisSpeeds.omegaRadiansPerSecond != 0.0) updateHeading();
    else if(Math.abs(kChassisSpeeds.vxMetersPerSecond) != 0 && Math.abs(kChassisSpeeds.vyMetersPerSecond) != 0)
      kChassisSpeeds.omegaRadiansPerSecond += SWERVE.DRIFT_CORRECTION_PID.calc();
  }

  @Override
  public void periodic() {
    driftCorrection();

    SwerveModuleState[] states = kDriveKinematics.toSwerveModuleStates(kChassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_VELCOCITY);
    for (int i = 0; i < states.length; i++) {
      kSwerveModules[i].set(states[i].speedMetersPerSecond/SWERVE.MAX_VELCOCITY*SWERVE.MAX_VOLTAGE, states[i].angle.getRadians());
      states[i].speedMetersPerSecond = Math.abs(kSwerveModules[i].getDriveVelocity());
    }
    kDriveOdometry.update(rotation(), states);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
      builder.setSmartDashboardType("Swerve Drive Train");
      builder.addDoubleProperty("X", odometry()::getX, null);
      builder.addDoubleProperty("Y", odometry()::getY, null);
      builder.addDoubleProperty("Rotation", rotation()::getDegrees, null);
      builder.addDoubleProperty("Front Left Angle", kSwerveModules[0]::getSteerAngle, null);
      builder.addDoubleProperty("Front Right Angle", kSwerveModules[1]::getSteerAngle, null);
      builder.addDoubleProperty("Back Left Angle", kSwerveModules[2]::getSteerAngle, null);
      builder.addDoubleProperty("Back Right Angle", kSwerveModules[3]::getSteerAngle, null);
  }
}
