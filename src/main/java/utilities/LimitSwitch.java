package utilities;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;

public class LimitSwitch extends DigitalInput{

    private boolean kNormalState;
    
    public LimitSwitch(int port){
        this(port, true);
    }

    public LimitSwitch(int port, boolean normalState){
        super(port);
        kNormalState = normalState;
    }

    @Override
    public boolean get() {
        return (kNormalState == super.get());
    }

    public boolean getRaw(){
        return super.get();
    }
}
