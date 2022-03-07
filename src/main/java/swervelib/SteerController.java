package swervelib;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public interface SteerController {
    double getReferenceAngle();

    void setReferenceAngle(double referenceAngleRadians);

    double getStateAngle();

    TalonFX getMotor();
}
