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
        int FRONT_LEFT_ROTATION = 4;
        int FRONT_LEFT_ENCODER = 0;
        
        int FRONT_RIGHT_DRIVE = 1;
        int FRONT_RIGHT_ROTATION = 5;
        int FRONT_RIGHT_ENCODER = 1;

        int BACK_LEFT_DRIVE = 2;
        int BACK_LEFT_ROTATION = 6;
        int BACK_LEFT_ENCODER = 2;

        int BACK_RIGHT_DRIVE = 3;
        int BACK_RIGHT_ROTATION = 7;
        int BACK_RIGHT_ENCODER = 3;

        double FRONT_LEFT_OFFSET = 230.0;
        double FRONT_RIGHT_OFFSET = 164.0;
        double BACK_LEFT_OFFSET = 103.0;
        double BACK_RIGHT_OFFSET = 193.0;
    }

    public interface CONTROLLER {
        int PORT = 0;
        double THRESHOLD = 0.5d;
        float DEBOUNCE_PERIOD = 0.5f;
        double DEAD_ZONE = 0.1d;

        int A = 1;
        int B = 2;
        int X = 3;
        int Y = 4;
        int LEFT_BUMPER = 5;
        int RIGHT_BUMPER = 5;
        int BACK = 7;
        int START = 8;
        int LEFT_JOYSTICK = 9;
        int RIGHT_JOYSTICK = 10;
        int RIGHT_TRIGGER = 11;
        int LEFT_TRIGGER = 12;
    
        int LEFT_X = 0;
        int LEFT_Y = 1;
        int RIGHT_X = 4;
        int RIGHT_Y = 5;
    
        int TOP = 11;
        int TOP_RIGHT = 12;
        int RIGHT = 13;
        int BOTTOM_RIGHT = 14;
        int BOTTOM = 15;
        int BOTTOM_LEFT = 16;
        int LEFT = 17;
        int TOP_LEFT = 18;
        int POV = 11;
    }

    public interface ELEVATOR {
        
    }

    public interface INTAKE {
        
    }

    public interface FEEDER {
        
    }

    public interface SHOOTER {
        
    }

    public interface ODOMETERY {
        double DRIVE_P = 0.04;
        double DRIVE_I = 0.04;
        double DRIVE_D = 0.05;
        double DRIVE_I_LIMIT = 0.025;

        double ROTATION_P = 0.0005;
        double ROTATION_I = 0.007;
        double ROTATION_D = 0.0;
        double ROTATION_I_LIMIT = 0.25;
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