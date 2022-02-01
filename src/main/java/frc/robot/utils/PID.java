package frc.robot.utils;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class PID {

    double p,i,d,limit,error, prevError,setpoint, errorSum,threshold;
    double previousTime = Timer.getFPGATimestamp();

    public PID(double p, double i, double d){
        this(0.0, p,i,d);
    }
    
    public PID(double setpoint, double p, double i, double d){
        this(setpoint, p,i,d, Constants.PID.DEFUALT_LIMIT,Constants.PID.DEFUALT_THRESHOLD);
    }

    public PID(double setpoint, double p, double i, double d, double limit, double threshold){
        this.p = p;
        this.i = i;
        this.d = d;
        this.limit = limit;
        this.setpoint = setpoint;
        this.threshold = threshold;
    }

    public double calculate(double current){
        error = setpoint - current;
        double dt = Timer.getFPGATimestamp() - previousTime;
        if(Math.abs(error) < limit) errorSum += error * i;
        double errorRate = (error - prevError) / dt;
        prevError = error;
        previousTime = Timer.getFPGATimestamp();
        return p*error + i *errorSum + d * errorRate;
    }

    public double calculate(double current, double setpoint){
        setSetpoint(setpoint);
        return calculate(current);
    }

    public double getP() {
        return p;
    }

    public double getI() {
        return i;
    }

    public double getD() {
        return d;
    }

    public double getSetpoint() {
        return setpoint;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public boolean atSetpoint(){
        return !(Math.abs((error)) < threshold);

    }

}
