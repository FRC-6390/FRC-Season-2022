package frc.robot.subsystems.utils;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Debouncer {
    
    private JoystickButton input;
    private double latest = 0;
    private double debounce_period;

    public Debouncer(JoystickButton input){
        this.input = input;
        this.debounce_period = .5;
    }
    public Debouncer(JoystickButton input, float period){
        this.input = input;
        this.debounce_period = period;
    }

    public boolean get(){
        double now = Timer.getFPGATimestamp();
        if(input.get())
            if((now-latest) > debounce_period){
                latest = now;
                return true;
            }
        return false;
    }
}
