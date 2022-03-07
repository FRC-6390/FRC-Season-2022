package utilities.controllib.controller;

import edu.wpi.first.wpilibj.Timer;

public class Debouncer {

    private static final double DEBOUNCE_PERIOD = 0.5;

    private double latest = 0;
    private double debounce_period;

    public Debouncer(){
        this.debounce_period = DEBOUNCE_PERIOD;
    }

    public Debouncer(double period){
        this.debounce_period = period;
    }

    public boolean get(){
        double now = Timer.getFPGATimestamp();
        if((now-latest) > debounce_period){
            latest = now;
            return true;
        }
        return false;
    }
}