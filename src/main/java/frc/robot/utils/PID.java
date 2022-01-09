package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;

public class PID {
    public double p, i, d ,f;

    public PID(double p, double i, double d, double f){
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
    }

    public SlotConfiguration getSlotConfiguration(){
        return new SlotConfiguration(){{
            kP = p;
            kI = i;
            kD = d;
            kF = f;
        }};
    }
}
