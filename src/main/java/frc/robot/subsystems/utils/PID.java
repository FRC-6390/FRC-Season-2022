package frc.robot.subsystems.utils;

import edu.wpi.first.wpilibj.Timer;

public class PID {

    double p,i,d,f,limit, error, prevError, errorSum,threashhold;
    double previousTime = Timer.getFPGATimestamp();
    
    public PID(double p, double i, double d, double f, double limit, double threashhold){
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.limit = limit;
        this.threashhold = threashhold;
    }

    public double calc(double error, double setpoint){
        this.error = error;
        double dt = Timer.getFPGATimestamp() - previousTime;
        if(Math.abs(error) < limit) errorSum += error * i;
        double errorRate = (error - prevError) / dt;
        prevError = error;
        previousTime = Timer.getFPGATimestamp();
        return p*error + i *errorSum + d * errorRate;
    }

    public double calc(double error){
        return calc(error, 0);
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
