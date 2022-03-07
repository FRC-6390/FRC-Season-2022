package utilities.controllib.pid;

import java.util.function.DoubleSupplier;

;

public class PID {
    
    private double kP = 0, 
                kI = 0, 
                kD = 0, 
                kILimit = 0, 
                kError = 0, 
                kPreviousError = 0, 
                kErrorSum = 0, 
                kPreviousTime = 0;

    private DoubleSupplier kSetPointSupplier, kCurrentSupplier;
    
    public double calc(double error){
        kError = error;
        double p = calculateProportional();
        double i = calculateIntegral();
        double d = calculateDerivative();
        kPreviousError = kError;
        kPreviousTime = System.nanoTime();
        return p+i+d;
    }

    public double calc(){
        kError = kSetPointSupplier.getAsDouble() - kCurrentSupplier.getAsDouble();
        return calc(kError);
    }

    private double calculateProportional(){
        return kP * kError;
    }

    private double calculateIntegral(){
        if(Math.abs(kError) < kILimit) kErrorSum += kError * kI;
        return kI * kErrorSum;
    }

    private double calculateDerivative(){
        return kD * (kError - kPreviousError) / (System.nanoTime() - kPreviousTime);
    }

    public void setP(double kP) {
        this.kP = kP;
    }

    public void setI(double kI) {
        this.kI = kI;
    }

    public void setD(double kD) {
        this.kD = kD;
    }

    public void setILimit(double kILimit) {
        this.kILimit = kILimit;
    }

    public void setkSetPointSupplier(DoubleSupplier kSetPointSupplier) {
        this.kSetPointSupplier = kSetPointSupplier;
    }

    public void setkCurrentSupplier(DoubleSupplier kCurrentSupplier) {
        this.kCurrentSupplier = kCurrentSupplier;
    }
}
