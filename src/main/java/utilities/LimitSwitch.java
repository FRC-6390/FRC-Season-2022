package utilities;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;

public class LimitSwitch extends DigitalInput{
    
    public LimitSwitch(int port){
        super(port);
    }

    @Override
    public boolean get() {
        return !super.get();
    }

    public boolean getRaw(){
        return super.get();
    }
}
