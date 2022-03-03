package frc.robot.subsystems.utils;

import edu.wpi.first.wpilibj.Timer;

public class PID {

    double p,i,d,limit, error, prevError, errorSum,threashhold;
    double previousTime = Timer.getFPGATimestamp();
    
    public PID(double p, double i, double d, double limit, double threashhold){
        this.p = p;
        this.i = i;
        this.d = d;
        this.limit = limit;
        this.threashhold = threashhold;
    }

    public double calc(double error){
        this.error = error;
        double dt = Timer.getFPGATimestamp() - previousTime;
        if(Math.abs(error) < limit) errorSum += error * i;
        double errorRate = (error - prevError) / dt;
        prevError = error;
        previousTime = Timer.getFPGATimestamp();
        return p*error + i *errorSum + d * errorRate;
    }

    public double calc(double current, double setpoint){
        return calc(setpoint - current);
    }

    public boolean threshhold(double error){
        return Math.abs(error) < threashhold;
    }

    public boolean threshhold(){
        return Math.abs(error) < threashhold;
    }

    public double getError(){
        return error;
    }

}
