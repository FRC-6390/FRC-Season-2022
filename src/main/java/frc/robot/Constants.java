package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI.Port;

public interface Constants {

    public interface SWERVE {
        double MAX_VOLTAGE = 12.0;
        double MAX_VELCOCITY = 6380.0/ 60* SdsModuleConfigurations.MK3_STANDARD.getDriveReduction() * SdsModuleConfigurations.MK3_STANDARD.getWheelDiameter();
        double MAX_ANGULAR = MAX_VELCOCITY / Math.hypot(ROBOT.TRACKWIDTH/2, ROBOT.WHEELBASE/2);
        Translation2d[] SWERVE_LOCATIONS = {ROBOT.FRONT_LEFT, ROBOT.FRONT_RIGHT, ROBOT.BACK_LEFT, ROBOT.BACK_RIGHT};
        int FRONT_LEFT_DRIVE = 0;
        int FRONT_LEFT_ROTATION = 0;
        int FRONT_LEFT_ENCODER = 0;
        
        int FRONT_RIGHT_DRIVE = 0;
        int FRONT_RIGHT_ROTATION = 0;
        int FRONT_RIGHT_ENCODER = 0;

        int BACK_LEFT_DRIVE = 0;
        int BACK_LEFT_ROTATION = 0;
        int BACK_LEFT_ENCODER = 0;

        int BACK_RIGHT_DRIVE = 0;
        int BACK_RIGHT_ROTATION = 0;
        int BACK_RIGHT_ENCODER = 0;

        double FRONT_LEFT_OFFSET = 0;
        double FRONT_RIGHT_OFFSET = 0;
        double BACK_LEFT_OFFSET = 0;
        double BACK_RIGHT_OFFSET = 0;
    }

    public interface CONTROLLER {
        
    }

    public interface ELEVATOR {
        
    }

    public interface INTAKE {
        
    }

    public interface FEEDER {
        
    }

    public interface SHOOTER {
        
    }

    public interface ROBOT {
        double TRACKWIDTH = 0.7112;
        double WHEELBASE = 0.7112;
        Translation2d FRONT_LEFT = new Translation2d(TRACKWIDTH/2, WHEELBASE/2);
        Translation2d FRONT_RIGHT = new Translation2d(TRACKWIDTH/2, -WHEELBASE/2);
        Translation2d BACK_LEFT = new Translation2d(-TRACKWIDTH/2, WHEELBASE/2);
        Translation2d BACK_RIGHT = new Translation2d(-TRACKWIDTH/2, -WHEELBASE/2);
        SerialPort.Port GYRO_PORT = SerialPort.Port.kUSB;    
    }
} 