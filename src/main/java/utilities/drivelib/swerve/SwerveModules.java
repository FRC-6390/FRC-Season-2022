package utilities.drivelib.swerve;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModules {

    private double kDriveVoltage;
    private double kMaxVelocity = 6390/60;
    private double kMaxAngular;

    // double MAX_VELCOCITY = 6380.0/ 60* SdsModuleConfigurations.MK4_L1.getDriveReduction() * SdsModuleConfigurations.MK4_L1.getWheelDiameter();
    // double MAX_ANGULAR = MAX_VELCOCITY / Math.hypot(ROBOT.TRACKWIDTH/2, ROBOT.WHEELBASE/2);

    public void set(SwerveModuleState state){
        double steerAngle = state.angle.getRadians();
        steerAngle %= (2 * Math.PI);
        if(steerAngle < 0) steerAngle += 2*Math.PI;

        double difference = steerAngle;  // - getSteerAngle()
        if(difference >= Math.PI) steerAngle -= 2*Math.PI;
        else if(difference < -Math.PI) steerAngle += 2* Math.PI;

        difference = steerAngle; // - getSteerAngle()
        
        if(difference > Math.PI/2 || difference < -Math.PI / 2){
            steerAngle += Math.PI;
            //drive voltage
        }

        steerAngle %= (2*Math.PI);
        if(steerAngle < 0) steerAngle += 2 * Math.PI;

         //driveController.setReferenceVoltage(driveVoltage);
        //steerController.setReferenceAngle(steerAngle);
    }
}
