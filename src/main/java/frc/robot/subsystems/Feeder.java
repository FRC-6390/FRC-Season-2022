package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    private static CANSparkMax feederMotor;

    static {
        feederMotor = new CANSparkMax(Constants.FEEDER.FEEDER_MOTOR, MotorType.kBrushless);
    }

    public Feeder(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        feederMotor.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        feederMotor.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}