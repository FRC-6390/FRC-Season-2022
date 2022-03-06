package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ELEVATOR;

public class ClimbArms extends SubsystemBase {
    private static Servo left, right;
    private static ShuffleboardTab tab = Shuffleboard.getTab("Climb");


    static {
        left = new Servo(ELEVATOR.LEFT_SERVO);
        right = new Servo(ELEVATOR.RIGHT_SERVO);
        // tab.getLayout("Climb", BuiltInLayouts.kList).addBoolean("Elevator Switch", () -> getBottomSwitch());
    }

    public static void open(){
        right.setAngle(90);
        left.setAngle(90);
    }

    public static void close(){
        
        right.setAngle(0);
        left.setAngle(180);
    }

    @Override
    public void periodic() {
        
    }
}