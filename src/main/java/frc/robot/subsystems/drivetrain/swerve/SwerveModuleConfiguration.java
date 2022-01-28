package frc.robot.subsystems.drivetrain.swerve;

public class SwerveModuleConfiguration {
    int driveMotorId;

    int rotationMotorId;

    int encouderId;

    double offset;

    public SwerveModuleConfiguration(int drive, int rotation, int encoder, double offset){
        driveMotorId = drive;
        rotationMotorId = rotation;
        encouderId = encoder;
        this.offset = offset;
    }
}
