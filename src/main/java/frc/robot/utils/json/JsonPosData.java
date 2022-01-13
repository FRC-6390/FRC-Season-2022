package frc.robot.utils.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class JsonPosData {

    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

    @SerializedName("theta")
    private double theta;

    @SerializedName("drive")
    private List<JsonPidData> drive = null;

    @SerializedName("rotation")
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