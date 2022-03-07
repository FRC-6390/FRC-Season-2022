package utilities.controllib.pid;

public class PIDFactory {
    
    private PID pid = new PID();

    public PIDFactory(){}

    public PIDFactory p(double p){
        pid.setP(p);
        return this;
    }

    public PIDFactory i(double i){
        pid.setI(i);
        return this;
    }

    public PIDFactory d(double d){
        pid.setD(d);
        return this;
    }

    public PIDFactory iLimit(double iLimit){
        pid.setILimit(iLimit);
        return this;
    }

    public PID build(){
        return pid;
    }

}
