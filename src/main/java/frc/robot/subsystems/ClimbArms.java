package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbArms extends SubsystemBase {
    static Servo left, right;

    static {
        left = new Servo(0);
        right = new Servo(1);
    }

    public ClimbArms(){
        // left.set(180);
        right.set(-180);
    }

    public static void setAngle(double angle){
        // left.setAngle(angle);
        right.setAngle(-angle);
    }

    @Override
    public void periodic() {
    }
}