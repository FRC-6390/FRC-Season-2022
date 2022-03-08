package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.INTAKE;

public class IntakeSubsystem extends SubsystemBase {

    static PWMSparkMax kLeftMotor, kRightMotor;

    static {
        kLeftMotor = new PWMSparkMax(INTAKE.LEFT);
        kRightMotor = new PWMSparkMax(INTAKE.RIGHT);
    }

    public static void setIntakeSpeed(double speed) {
        kLeftMotor.set(speed);
        kRightMotor.set(-speed);
    }

    public static void stop(){
        kLeftMotor.stopMotor();
        kRightMotor.stopMotor();
    }

    @Override
    public void periodic() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Intake");
        builder.addDoubleProperty("Left Motor Speed", kLeftMotor::get, null);
        builder.addDoubleProperty("Right Motor Speed", kRightMotor::get, null);
    }
}
