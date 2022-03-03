package frc.robot.subsystems.utils.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class JsonPosData {

    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

    @SerializedName("theta")
    private double theta;

    @SerializedName("customPID")
    private boolean customPID;

    @SerializedName("drivePID")
    private List<JsonPidData> drive = null;

    @SerializedName("rotationPID")
    private List<JsonPidData> rotation = null;


    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }


    public double getTheta() {
        return theta;
    }
    public void setTheta(double theta) {
        this.theta = theta;
    }

    public boolean getCustomPID() {
        return customPID;
    }
    public void setCustomPID(boolean customPID) {
        this.customPID = customPID;
    }

    public List<JsonPidData> getDrive(){
        return drive;
    }
    public void setDrive(List<JsonPidData> drive){
        this.drive = drive;
    }

    public List<JsonPidData> getRotation(){
        return rotation;
    }
    public void setRotation(List<JsonPidData> rotation){
        this.rotation = rotation;
    }
}