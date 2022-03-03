package frc.robot.subsystems.utils.json;


import com.google.gson.annotations.SerializedName;

public class JsonPidData {

    
    @SerializedName("p")
    private double p;

    @SerializedName("i")
    private double i;

    @SerializedName("d")
    private double d;

    @SerializedName("ilimit")
    private double iLimit;

    @SerializedName("threshold")
    private double threshold;


    public double getP() {
        return p;
    }
    public void setP(double p) {
        this.p = p;
    }

    public double getI() {
        return i;
    }
    public void seti(double i) {
        this.i = i;
    }

    public double getD() {
        return d;
    }
    public void setD(double d) {
        this.d = d;
    }

    public double getILimit() {
        return iLimit;
    }
    public void setILimit(double iLimit) {
        this.iLimit = iLimit;
    }

    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}
