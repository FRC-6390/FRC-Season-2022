package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    private static CANSparkMax feederMotor;
    private static CANSparkMax left;
    private static CANSparkMax right;


    static {
        feederMotor = new CANSparkMax(Constants.FEEDER.FEEDER_MOTOR, MotorType.kBrushless);
        left = new CANSparkMax(15, MotorType.kBrushless);
        right = new CANSparkMax(16, MotorType.kBrushless);

    }

    public Feeder(){
        setMotorsIdleMode(IdleMode.kBrake);
        
    }

    public static void setMotorSpeed(double velocity){
        left.set(velocity);
        right.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        left.setIdleMode(mode);
        right.setIdleMode(mode);

    }

    @Override
    public void periodic() {
  
    }
}