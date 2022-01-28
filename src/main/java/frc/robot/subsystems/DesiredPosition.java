package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;

public class DesiredPosition {

    private Pose2d cords;
    private PIDController drivePID, rotationPID;

    public DesiredPosition(Pose2d pos,PIDController drive ,PIDController rotation) {
        cords = pos;
        drivePID = drive;
        rotationPID = rotation;
    }
    
    public ChassisSpeeds getChassisSpeeds(Pose2d currentPos){
        //NOTE may need a pidcontroller for each
        double x = drivePID.calculate(currentPos.getX(), cords.getX());
        double y = drivePID.calculate(currentPos.getY(), cords.getY());
        double t = rotationPID.calculate(currentPos.getRotation().getRadians(), cords.getRotation().getRadians());
        return ChassisSpeeds.fromFieldRelativeSpeeds(x, y, t, currentPos.getRotation());
    }

    public boolean threashhold(){
        return drivePID.atSetpoint() && rotationPID.atSetpoint();
    }
}