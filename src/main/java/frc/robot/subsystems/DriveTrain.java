package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ROBOT;
import frc.robot.Constants.SWERVE;;

public class DriveTrain extends SubsystemBase {

  private SwerveDriveKinematics kinematics;
  private SwerveDriveOdometry odometry;
  private SwerveModule[] swerveModules = {};
  private AHRS gyro;
  private ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0,0,0);

  public DriveTrain() {
    gyro = new AHRS(ROBOT.GYRO_PORT); 

    swerveModules[0] = Mk3SwerveModuleHelper.createFalcon500(Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_LEFT_DRIVE, SWERVE.FRONT_LEFT_ROTATION, SWERVE.FRONT_LEFT_ENCODER, SWERVE.FRONT_LEFT_OFFSET);
    swerveModules[1] = Mk3SwerveModuleHelper.createFalcon500(Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.FRONT_RIGHT_DRIVE, SWERVE.FRONT_RIGHT_ROTATION, SWERVE.FRONT_RIGHT_ENCODER, SWERVE.FRONT_RIGHT_OFFSET);
    swerveModules[2] = Mk3SwerveModuleHelper.createFalcon500(Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_LEFT_DRIVE, SWERVE.BACK_LEFT_ROTATION, SWERVE.BACK_LEFT_ENCODER, SWERVE.BACK_LEFT_OFFSET);
    swerveModules[3] = Mk3SwerveModuleHelper.createFalcon500(Mk3SwerveModuleHelper.GearRatio.STANDARD, SWERVE.BACK_RIGHT_DRIVE, SWERVE.BACK_RIGHT_ROTATION, SWERVE.BACK_RIGHT_ENCODER, SWERVE.BACK_RIGHT_OFFSET);

    kinematics = new SwerveDriveKinematics(SWERVE.SWERVE_LOCATIONS);
    odometry = new SwerveDriveOdometry(kinematics, gyro.getRotation2d());
  }

  public void zero(){
    gyro.zeroYaw();
  }

  public void drive(ChassisSpeeds speeds){
    chassisSpeeds = speeds;
  }

  @Override
  public void periodic() {
    SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SWERVE.MAX_VELCOCITY);

    for (int i = 0; i < states.length; i++) swerveModules[i].set(states[i].speedMetersPerSecond/SWERVE.MAX_VELCOCITY*SWERVE.MAX_VOLTAGE, states[i].angle.getRadians());
  }
}
