package frc.robot.utils;

public class Module {
    Encoder encoderId;
    int driveMotorId;
    int rotationMotorId;

    public Module(Encoder encoderId, int driveMotorId, int rotationMotorId){
        this.encoderId = encoderId;
        this.driveMotorId = driveMotorId;
        this.rotationMotorId = rotationMotorId;
    }
    
    public int getDriveMotorId() {
        return driveMotorId;
    }

    public Encoder getEncoderId() {
        return encoderId;
    }

    public int getRotationMotorId() {
        return rotationMotorId;
    }
}
