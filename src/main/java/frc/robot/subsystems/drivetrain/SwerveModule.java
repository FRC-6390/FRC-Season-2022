package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants;
import frc.robot.utils.Units.units;

public class SwerveModule {

    private final TalonFX driveMotor, rotationMotor;
    private final CANCoder moduleEncoder;
    private CANCoderConfiguration moduleEncoderConfiguration;
    private TalonFXConfiguration rotationConfiguration, driveConfiguration;
    
    public SwerveModule(TalonFX driveMotor, TalonFX rotationMotor, CANCoder moduleEncoder, double offset) {
      this.driveMotor = driveMotor;
      this.rotationMotor = rotationMotor;
      this.moduleEncoder = moduleEncoder;

      rotationMotor.configFactoryDefault();
      driveMotor.configFactoryDefault();
      moduleEncoder.configFactoryDefault();
      
      rotationConfiguration = new TalonFXConfiguration(){{
        slot0 = Constants.SWERVE.ROTATION_PID.getSlotConfiguration();
        remoteFilter0.remoteSensorDeviceID = moduleEncoder.getDeviceID();
        remoteFilter0.remoteSensorSource = RemoteSensorSource.CANCoder;
        primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
      }};
      
      driveConfiguration = new TalonFXConfiguration(){{
        slot0 = Constants.SWERVE.DRIVE_PID.getSlotConfiguration();
      }};

      moduleEncoderConfiguration = new CANCoderConfiguration(){{
        magnetOffsetDegrees = new Rotation2d(offset).getDegrees();
      }};

      rotationMotor.configAllSettings(rotationConfiguration);
      driveMotor.configAllSettings(driveConfiguration);
      moduleEncoder.configAllSettings(moduleEncoderConfiguration);

      rotationMotor.setNeutralMode(NeutralMode.Brake);
      driveMotor.setNeutralMode(NeutralMode.Brake);
    }
      
    public void setDesiredState(SwerveModuleState desiredState){
      Rotation2d currentRotation = getAngle();
      SwerveModuleState state = SwerveModuleState.optimize(desiredState, currentRotation);
      Rotation2d rotationDelta = state.angle.minus(currentRotation);

      double deltaTicks = (rotationDelta.getDegrees() / 360) * Constants.SENSOR.EXTERNAL_ENCODER_RESOLUTION;
      double currentTicks = moduleEncoder.getPosition() / moduleEncoder.configGetFeedbackCoefficient();
      double desiredTicks = currentTicks + deltaTicks;
      double feetPerSecond = Units.metersToFeet(state.speedMetersPerSecond);

      rotationMotor.set(ControlMode.Position, desiredTicks);      
      driveMotor.set(ControlMode.PercentOutput, feetPerSecond / Constants.SWERVE.MAX_SPEED.get(units.FEET));
    }
  
    public SwerveModuleState getState() {
      return new SwerveModuleState(nativeUnitsToDistanceMeters(driveMotor.getSelectedSensorVelocity()*10), getAngle());
    }

    public double getRawAngle() { 
      return  moduleEncoder.getAbsolutePosition();
    }

    public Rotation2d getAngle() {
      return Rotation2d.fromDegrees(moduleEncoder.getAbsolutePosition());
    }

    public double getWheelMovedMeters(){
      return nativeUnitsToDistanceMeters(driveMotor.getSelectedSensorPosition());
    }

    private double nativeUnitsToDistanceMeters(double sensorCounts){
      double motorRotations = (double)sensorCounts / Constants.SENSOR.INTERNAL_ENCODER_RESOLUTION;
      double wheelRotations = motorRotations / Constants.SWERVE.DRIVE_GEAR_RATIO;
      double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(2));
      return positionMeters;
    } 
}

