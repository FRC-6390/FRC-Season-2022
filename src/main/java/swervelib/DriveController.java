package swervelib;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public interface DriveController {
    void setReferenceVoltage(double voltage);

    double getStateVelocity();

    TalonFX getMotor();
}
