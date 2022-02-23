package frc.robot.subsystems.swerve;

import com.ctre.phoenix.led.ColorFlowAnimation.Direction;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import frc.robot.Constants;
import frc.robot.utils.Module;

public class SwerveModule {

    private final TalonFX driveMotor, rotationMotor;
    private final CANCoder moduleEncoder;
    private final Module module;
    private double offset = 0;
    private CANCoderConfiguration moduleEncoderConfiguration;
    private TalonFXConfiguration rotationConfiguration, driveConfiguration;
    private NetworkTableEntry customOffset, useCustom;
    
    public SwerveModule(ShuffleboardLayout layout, Module module) {
        this.module = module;
        offset = module.offset.getDegrees();
        //Motors
        driveMotor = new TalonFX(module.drive);
        rotationMotor = new TalonFX(module.rotation);

        rotationMotor.configFactoryDefault();
        driveMotor.configFactoryDefault();
        //Module Encoders
        moduleEncoder = new CANCoder(module.encoder);
        moduleEncoder.configFactoryDefault();
        
        rotationConfiguration = new TalonFXConfiguration(){{
          slot0.kP = 0.2;
          slot0.kI = 0.0001;
          slot0.kD = 0.1;
          remoteFilter0.remoteSensorDeviceID = moduleEncoder.getDeviceID();
          remoteFilter0.remoteSensorSource = RemoteSensorSource.CANCoder;
          primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
        }};
        
        rotationMotor.configAllSettings(rotationConfiguration);
        rotationMotor.setNeutralMode(NeutralMode.Brake);

        driveConfiguration = new TalonFXConfiguration(){{
          slot0.kP = 1;
          slot0.kI = 0;
          slot0.kD = 0;
          slot0.kF = 0.1;
        }};
        driveMotor.configAllSettings(driveConfiguration);
        driveMotor.setNeutralMode(NeutralMode.Brake);

        setEncoderConfiguration(offset);

        layout.addNumber("Raw Angle",() -> getRawAngle());
        layout.addNumber("Angle", () -> getAngle().getDegrees());
        layout.addNumber("Wheel Moved Meters", () -> getWheelMovedMeters());
        customOffset = layout.add("Custom Offset", getOffset()).getEntry();
        useCustom = layout.add("Use Custom Offset", false).getEntry();
        new NetworkButton(useCustom).whenPressed(() -> updateOffsets());
        layout.addNumber("Offset",() -> getOffset());
        // layout.addNumber("Drive Output Voltage", () -> driveMotor.getMotorOutputVoltage());
        // layout.addNumber("Rotation Output Voltage", () -> rotationMotor.getMotorOutputVoltage());
    }

    private void setEncoderConfiguration(double off){
        moduleEncoderConfiguration = new CANCoderConfiguration(){{
            magnetOffsetDegrees = off;
            absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
            sensorDirection = false;
        }};
          moduleEncoder.configAllSettings(moduleEncoderConfiguration);
    }

      
    public void setDesiredState(SwerveModuleState desiredState){
        //updateOffsets();
        Rotation2d currentRotation = getAngle();
        SwerveModuleState state = SwerveModuleState.optimize(desiredState, currentRotation);

        Rotation2d rotationDelta = state.angle.minus(currentRotation);

        double deltaTicks = (rotationDelta.getDegrees() / 360d) * 4096.0d;
        double currentTicks = moduleEncoder.getPosition() / moduleEncoder.configGetFeedbackCoefficient();
        double desiredTicks = currentTicks + deltaTicks;    

        rotationMotor.set(ControlMode.Position, desiredTicks);

        double feetPerSecond = Units.metersToFeet(state.speedMetersPerSecond);
        
        driveMotor.set(ControlMode.PercentOutput, feetPerSecond / Constants.SWERVE.MAX_VELCOCITY);

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

    public double getOffset(){
        return offset;
    }

    public void updateOffsets(){
      double offset = useCustom.getBoolean(false) ? customOffset.getDouble(this.offset) : module.offset.getDegrees();
        this.offset = offset;
        setEncoderConfiguration(offset);
    }

    private double nativeUnitsToDistanceMeters(double sensorCounts){
      double motorRotations = (double)sensorCounts / 2048.0d;
      double wheelRotations = motorRotations / 8.14d;
      double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(2));
      return positionMeters;
    } 
}

