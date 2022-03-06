package frc.robot.subsystems;
import java.util.ArrayList;
import java.util.Iterator;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AUTO;
import frc.robot.subsystems.utils.PID;

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
        return relative(x,y,previous().desiredPos.getRotation());  
    }

    public DesiredPositionFactory relative(double x, double y, double theta){
        return relative(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory relative(double x, double y, Rotation2d theta){
        return relative(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory relative(Pose2d pos){
        cords.add(new DesiredPosition(previous().getPos().relativeTo(pos)));
        return this;
    }

    public DesiredPositionFactory to(double x, double y, double theta){
        return to(x,y,Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory to(double x, double y, Rotation2d theta){
        return to(new Pose2d(x,y,theta));
    }

    public DesiredPositionFactory to(double x, double y){
        return to(x,y,previous().desiredPos.getRotation());
    }

    public DesiredPositionFactory to(Pose2d pos){
        cords.add(new DesiredPosition(pos));
        return this;
    }

    public DesiredPositionFactory withCommand(Command command){
        return withCommand(new DesiredCommand(command));
    }

    public DesiredPositionFactory withCommand(Command command, boolean waitToFinish){
        return withCommand(new DesiredCommand(command), waitToFinish);
    }

    public DesiredPositionFactory withCommand(DesiredCommand command){
        return withCommand(command, false);
    }

    public DesiredPositionFactory withCommand(DesiredCommand command, boolean waitToFinish){
        previous().setCommand(command, waitToFinish);
        return this;
    }

    public DesiredPositionFactory withPID(PID rPID){
        return withPID(AUTO.DEFUALT_X_PID, AUTO.DEFUALT_Y_PID, rPID);
    }

    public DesiredPositionFactory withPID(PID xPID, PID yPID){
        return withPID(xPID, yPID, AUTO.DEFUALT_ROTATION_PID);
    }

    public DesiredPositionFactory withPID(PID xPID, PID yPID,PID rPID){
        previous().setPID(xPID, yPID, rPID);
        return this;
    }

    public DesiredPositionFactory rotate(double theta){
        return rotate(Rotation2d.fromDegrees(theta));
    }

    public DesiredPositionFactory rotate(Rotation2d theta){
        cords.add(new DesiredPosition(new Pose2d(previous().getPos().getTranslation(), theta)));
        return this;
    }

    public DesiredPositionFactory origin(){
        cords.add(cords.get(0));
        return this;
    }

    public Iterator<DesiredPosition> build(){
        return cords.iterator();
    }

    private DesiredPosition previous(){
        return cords.get(cords.size()-1);
    }
}
