package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ELEVATOR;

public class ClimbArms extends SubsystemBase {
    private static Servo left, right;

    static {
        left = new Servo(ELEVATOR.LEFT_SERVO);
        right = new Servo(ELEVATOR.RIGHT_SERVO);
    }

    public static void open(){
        left.setAngle(ELEVATOR.SERVO_MIN);
        right.setAngle(ELEVATOR.SERVO_MAX);
    }

    public static void close(){
        right.setAngle(ELEVATOR.SERVO_MIN);
        left.setAngle(ELEVATOR.SERVO_MAX);
    }

    @Override
    public void periodic() {
        
    }
}