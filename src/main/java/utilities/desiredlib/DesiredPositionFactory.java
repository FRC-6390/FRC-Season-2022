package utilities.desiredlib;
import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import utilities.controllib.pid.PID;

public class DesiredPositionFactory {

    private ArrayList<DesiredPosition> cords = new ArrayList<>();

    public DesiredPositionFactory(double x, double y, double theta){
        this(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory(double x, double y,  Rotation2d theta){
        this(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory(Pose2d pos){
        cords.add(new DesiredPosition(pos));   
    }

    public DesiredPositionFactory to(double x, double y){
        return to(x,y,previous().getSetPoint().getRotation());  
    }

    public DesiredPositionFactory to(double x, double y, double theta){
        return to(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory to(double x, double y, Rotation2d theta){
        return to(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory to(Pose2d pos){
        cords.add(new DesiredPosition(previous().getSetPoint().relativeTo(pos)));
        return this;
    }

    public DesiredPositionFactory withCommand(Command command){
        previous().setCommand(command);
        return this;
    }

    public DesiredPositionFactory forSeconds(double seconds){
        previous().setSeconds(seconds);
        return this;
    }

    public DesiredPositionFactory withXPID(PID xPID){
        previous().setXPID(xPID);
        return this;
    }

    public DesiredPositionFactory withYPID(PID yPID){
        previous().setYPID(yPID);
        return this;    
    }

    public DesiredPositionFactory withRPID(PID rPID){
        previous().setRPID(rPID);
        return this;    
    }

    public DesiredPositionFactory withPID(PID xPID, PID yPID,PID rPID){
        withXPID(xPID);
        withYPID(yPID);
        withRPID(rPID);
        return this;
    }

    public DesiredPositionFactory rotate(double theta){
        return rotate(Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory rotate(Rotation2d theta){
        cords.add(new DesiredPosition(new Pose2d(previous().getSetPoint().getTranslation(), theta)));
        return this;
    }

    public Iterator<DesiredPosition> build(){
        return cords.iterator();
    }

    private DesiredPosition previous(){
        return cords.get(cords.size()-1);
    }
}