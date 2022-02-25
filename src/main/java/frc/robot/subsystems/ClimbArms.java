package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ELEVATOR;

public class ClimbArms extends SubsystemBase {
    private static Servo left, right;

    public ClimbArms(){
        left = new Servo(ELEVATOR.LEFT_SERVO);
        right = new Servo(ELEVATOR.RIGHT_SERVO);

        //sets the servos resting position
        left.set(180);
        right.set(-180);
    }

    public static void setAngle(double angle){
        left.setAngle(angle);
        right.setAngle(-angle);
    }

    @Override
    public void periodic() {
    }
}