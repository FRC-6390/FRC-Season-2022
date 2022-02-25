package frc.robot.subsystems;
import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.AUTO;
import frc.robot.utils.PID;

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

    public DesiredPositionFactory relative(double x, double y){
        return relative(x,y,cords.get(cords.size()-1).desiredPos.getRotation());  
    }

    public DesiredPositionFactory relative(double x, double y, double theta){
        return relative(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory relative(double x, double y, Rotation2d theta){
        return relative(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory relative(Pose2d pos){
        cords.add(new DesiredPosition(cords.get(cords.size()-1).getPos().relativeTo(pos)));
        return this;
    }

    public DesiredPositionFactory to(double x, double y, double theta){
        return to(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory to(double x, double y, Rotation2d theta){
        return to(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory to(double x, double y){
        return to(x,y,cords.get(cords.size()-1).desiredPos.getRotation());
    }

    public DesiredPositionFactory to(Pose2d pos){
        cords.add(new DesiredPosition(pos));
        return this;
    }

    public DesiredPositionFactory withPID(PID rPID){
        return withPID(AUTO.DEFUALT_X_PID, AUTO.DEFUALT_Y_PID, rPID);
    }

    public DesiredPositionFactory withPID(PID xPID, PID yPID){
        return withPID(xPID, yPID, AUTO.DEFUALT_ROTATION_PID);
    }

    public DesiredPositionFactory withPID(PID xPID, PID yPID,PID rPID){
        cords.get(cords.size()-1).setPID(xPID, yPID, rPID);
        return this;
    }

    public DesiredPositionFactory rotate(double theta){
        return rotate(Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory rotate(Rotation2d theta){
        cords.add(new DesiredPosition(new Pose2d( cords.get(cords.size()-1).getPos().getTranslation(), theta)));
        return this;
    }

    public DesiredPositionFactory origin(){
        cords.add(cords.get(0));
        return this;
    }

    public Iterator<DesiredPosition> build(){
        return cords.iterator();
    }
}
