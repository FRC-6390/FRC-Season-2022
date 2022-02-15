package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants.AUTO;
import frc.robot.utils.PID;

public class DesiredPosition {

    private PID xPID, yPID, rPID;
    public boolean ignoreRotation = false, ignoreDrive = false;
    public double x, y, t;

    public DesiredPosition(double t) {
        this(new Pose2d(0,0,Rotation2d.fromDegrees(t)));
        ignoreDrive = true;
    }

    public DesiredPosition(double x, double y) {
        this(new Pose2d(x,y,Rotation2d.fromDegrees(0)));
        ignoreRotation = true;
    }

    public DesiredPosition(double x, double y, double t) {
        this(new Pose2d(x,y,Rotation2d.fromDegrees(t)));
    }

    public DesiredPosition(Pose2d pos) {
        this(pos, AUTO.DEFUALT_DRIVE_PID, AUTO.DEFUALT_ROTATION_PID);
    }

    public DesiredPosition(Pose2d pos, PID drive ,PID rotation) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.t = pos.getRotation().getDegrees();
        xPID = new PID(pos.getX(), drive.getP(), drive.getI(), drive.getD());
        yPID = new PID(pos.getY(), drive.getP(), drive.getI(), drive.getD());;
        rPID = rotation;
        xPID.setSetpoint(pos.getX());
        yPID.setSetpoint(pos.getY());
        rPID.setSetpoint(pos.getRotation().getDegrees());
    }
    
    public ChassisSpeeds getChassisSpeeds(DriveTrain currentPos){
        double x = ignoreDrive ? 0 : xPID.calculate(currentPos.pos().getX() * 2.5);
        double y = ignoreDrive ? 0 : yPID.calculate(currentPos.pos().getY() * 2.5);
        double t = ignoreRotation ? 0 : rPID.calculate(currentPos.pos().getRotation().getDegrees());
        //System.out.println(ignoreDrive);
        return ChassisSpeeds.fromFieldRelativeSpeeds(x, y, t, currentPos.rotation());
    }

    public boolean threashhold(){
        return ((xPID.atSetpoint() && yPID.atSetpoint()) || ignoreDrive) && (rPID.atSetpoint() || ignoreRotation);
    }

    public PID getDrivePID(){
        return xPID;
    }

    public PID getRotationPID(){
        return rPID;
    }

    public double x(){
        return x;
    }

    public double y(){
        return y;
    }

    public double theta(){
        return t;
    }

}