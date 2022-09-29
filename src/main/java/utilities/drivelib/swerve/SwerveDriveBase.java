package utilities.drivelib.swerve;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveDriveBase {

    private SwerveModules kModules[];
    
    public void set(SwerveModuleState... state){
        for (int i = 0; i < state.length; i++) {
            kModules[i].set(state[i]);
        }
    }

    
}
