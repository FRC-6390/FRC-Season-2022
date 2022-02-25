package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.AUTO;
import frc.robot.utils.PID;

public class DesiredPosition {

    public Pose2d desiredPos;
    public PID xPID, yPID, rPID;
    public Transform2d error;
    //pid
    public DesiredPosition(Pose2d desiredPos) {
        this(desiredPos, AUTO.DEFUALT_X_PID, AUTO.DEFUALT_Y_PID, AUTO.DEFUALT_ROTATION_PID);
    }

    public DesiredPosition(Pose2d desiredPos, PID xPID, PID yPID, PID rPID) {
        this.desiredPos = desiredPos;
        this.xPID = xPID;
        this.yPID = yPID;
        this. rPID = rPID;
    }

    public ChassisSpeeds getChassisSpeeds(Pose2d odometry){
        error = desiredPos.minus(odometry);
        
        return ChassisSpeeds.fromFieldRelativeSpeeds(xPID.calc(error.getX()),yPID.calc(error.getY()),rPID.calc(error.getRotation().getDegrees()), odometry.getRotation());
    }

    public boolean threashhold(){
        return error != null ? xPID.threshhold(error.getX()) && yPID.threshhold(error.getY()) && rPID.threshhold(error.getRotation().getDegrees()) : false;
    }

    public PID getXPID(){
        return xPID;
    }

    public PID getYPID(){
        return yPID;
    }

    public PID getRotationPID(){
        return rPID;
    }

    public Pose2d getPos(){
        return desiredPos;
    }

    public void setXPID(PID xPID){
        setDrivePID(xPID, yPID);
    }
    
    public void setYPID(PID yPID){
        setDrivePID(xPID, yPID);
    }

    public void setRotationPID(PID rPID){
        setPID(xPID, yPID, rPID);
    }

    public void setDrivePID(PID xPID,PID yPID){
        setPID(xPID, yPID, rPID);
    }
    
    public void setPID(PID xPID,PID yPID,PID rPID){
        this.xPID = xPID;
        this.yPID = yPID;
        this.rPID = rPID;
    }
}