package swervelib;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public interface SwerveModule {
    double getDriveVelocity();

    double getSteerAngle();

    void set(double driveVoltage, double steerAngle);

    TalonFX getDriveMotor();

    TalonFX getSteerMotor();
}
