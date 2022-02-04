package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private static CANSparkMax intakeLeft, intakeRight;

    static {
        intakeLeft = new CANSparkMax(Constants.INTAKE.LEFT, MotorType.kBrushless);
        intakeRight = new CANSparkMax(Constants.INTAKE.RIGHT, MotorType.kBrushless);
    }

    public IntakeSubsystem(){
        setMotorsIdleMode(IdleMode.kCoast);
    }

    public static void setMotorSpeed(double velocity){
        intakeLeft.set(velocity);
        intakeRight.set(velocity);
    }

    public static void setMotorsIdleMode(IdleMode mode){
        intakeLeft.setIdleMode(mode);
        intakeRight.setIdleMode(mode);
    }

    @Override
    public void periodic() {
  
    }
}
