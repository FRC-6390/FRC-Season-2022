package utilities.drivelib;

import utilities.drivelib.differential.DifferentialDriveBaseFactory;
import utilities.drivelib.swerve.SwerveDriveBaseFactory;

public class DriveTrainFactory {
    
    public static DifferentialDriveBaseFactory createDifferentialDrive(){
        return new DifferentialDriveBaseFactory();
    }

    public static SwerveDriveBaseFactory createSwerveDrive(){
        return new SwerveDriveBaseFactory();
    }
}