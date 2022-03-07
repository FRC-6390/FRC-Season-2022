package utilities.desiredlib;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import utilities.controllib.pid.PID;

public class DesiredPosition {
    
    private Pose2d kSetPoint;
    private PID kXPID, kYPID, kRPID;
    private Command kCommand;
    private Transform2d kError;
    private double kVXMetersPerSecond, kVYMetersPerSecond, kOmegaRadiansPerSecond, kSeconds;
    private double kTimeStart = 0;

    public DesiredPosition(Pose2d setPoint){
        this.kSetPoint = setPoint;
    }

    public ChassisSpeeds getChassisSpeeds(Pose2d odometry){
        kTimeStart = kTimeStart == 0 ? System.currentTimeMillis()/1000 : kTimeStart; 
        kError = kSeconds > 0 ? interpolated(odometry) : kSetPoint.minus(odometry);
        kVXMetersPerSecond = kXPID.calc(kError.getX());
        kVYMetersPerSecond = kYPID.calc(kError.getY());
        kOmegaRadiansPerSecond = kRPID.calc(kError.getRotation().getDegrees());
        return ChassisSpeeds.fromFieldRelativeSpeeds(kVXMetersPerSecond, kVYMetersPerSecond, kOmegaRadiansPerSecond, odometry.getRotation());
    }

    private Transform2d interpolated(Pose2d odometry){
        double t = ((System.currentTimeMillis()/1000) - kTimeStart) / kSeconds;
        Pose2d interpolation = odometry.interpolate(kSetPoint, t);
        return new Transform2d(interpolation.getTranslation(), interpolation.getRotation());
    }
   
    public boolean threshold(){
        return false;
    }

    public Pose2d getSetPoint() {
        return kSetPoint;
    }

    public PID getXPID() {
        return kXPID;
    }
    
    public PID getYPID() {
        return kYPID;
    }

    public PID getRPID() {
        return kRPID;
    }

    public Command getCommand() {
        return kCommand;
    }

    public Transform2d getError() {
        return kError;
    }

    public double getOmegaRadiansPerSecond() {
        return kOmegaRadiansPerSecond;
    }

    public double getVxMetersPerSecond() {
        return kVXMetersPerSecond;
    }

    public double getVyMetersPerSecond() {
        return kVYMetersPerSecond;
    }

    public void setCommand(Command command) {
        this.kCommand = command;
    }

    public void setRPID(PID rPID) {
        this.kRPID = rPID;
    }

    public void setXPID(PID xPID) {
        this.kXPID = xPID;
    }

    public void setYPID(PID yPID) {
        this.kYPID = yPID;
    }

    public void setSeconds(double seconds){
        this.kSeconds = seconds;
    }
}
