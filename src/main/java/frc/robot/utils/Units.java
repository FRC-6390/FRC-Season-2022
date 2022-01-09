package frc.robot.utils;
public class Units {
    public enum units {
        METERS,
        INCHES,
        CENTIMETERS,
        FEET
    }
    double value;
    units unit;

    public Units(double value, units unit){
        this.value = value;
        this.unit = unit;
    }

    public double get(units unit){
        if(this.unit.equals(unit)){
            return value;
        }

        switch (unit) {
            case METERS:
                switch (this.unit) {
                    case CENTIMETERS: return value * 100.00d;
                    case FEET: return value * 3.28d;
                    case INCHES: return value * 39.37d;
                    default: break;
                }
            case CENTIMETERS:   
                switch (this.unit) {
                    case METERS: return value / 100.00d;
                    case FEET: return value / 30.48d;
                    case INCHES: return value / 2.54d;     
                    default: break;
                }
            case FEET:
                switch (this.unit) {
                    case CENTIMETERS: return value * 30.48d;
                    case METERS: return value / 3.28d;
                    case INCHES: return value * 12.00d;
                    default: break;
                }
            case INCHES:
                switch (this.unit) {
                    case CENTIMETERS: return value * 2.54d;
                    case FEET: return value / 12.00d;
                    case METERS: return value / 39.37d;     
                    default: break;
                }
            default: throw new NoCoversionException("Case for "+this.unit+" to "+unit + " does not exist");
        }
    }
}

class NoCoversionException extends RuntimeException{
    NoCoversionException(String message){
        super(message);
    }
}