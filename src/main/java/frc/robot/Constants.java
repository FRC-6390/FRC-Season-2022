// package frc.robot;

// import frc.robot.utils.Units.units;
// import frc.robot.utils.Units;
// import edu.wpi.first.wpilibj.SerialPort;
// import frc.robot.utils.Encoder;
// import frc.robot.utils.PID;
// import frc.robot.utils.Module;

// public interface Constants {

//     //Please only put constants in one section and link it to others if neeeded, if you feel none of the sections below fit
//     //what you are trying to add, create a new interface with the name of the section your constant falls under, be as general as possible
//     //NOTE all variables and interface names must be CAPS with variables having "_" for spaces and interfaces having no "_"

//     /**
//      * put any sweremodules here with the Module class
//      */
//     interface SWERVE {
//         Module FRONT_LEFT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
//         Module FRONT_RIGHT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
//         Module BACK_LEFT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
//         Module BACK_RIGHT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);

//         Units MAX_SPEED = new Units(0.3, units.METERS);
//         double PRECENT_MAX_SPEED = 0.3;
        
//         double FRONT_LEFT_OFFSET = 230.0;
//         double FRONT_RIGHT_OFFSET = 164.0;
//         double BACK_LEFT_OFFSET = 103.0;
//         double BACK_RIGHT_OFFSET = 193.0;

//         double DRIVE_GEAR_RATIO = 8.16;     //6.12
//         double ROTATION_GEAR_RATIO = 12.8; 

//         PID DRIVE_PID = new PID(0, 0, 0, 0);
//         PID ROTATION_PID = new PID(1, 0, 0, 0);

//         Units LOCATION_FROM_CENTER = new Units(0.3302, units.METERS);
//     }
    
//     /**
//      * Put any motors here
//      */
//     interface MOTOR {
//         int FRONT_LEFT_DRIVE = 0;
//         int FRONT_RIGHT_DRIVE = 1;
//         int BACK_LEFT_DRIVE = 2;
//         int BACK_RIGHT_DRIVE = 3;

//         int FRONT_LEFT_ROTATION = 4;
//         int FRONT_RIGHT_ROTATION = 5;
//         int BACK_LEFT_ROTATION = 6;
//         int BACK_RIGHT_ROTATION = 7;

//         int INTAKE_LEFT = 8;
//         int INTAKE_RIGHT = 9;

//         int FEEDER = 0/*TBD*/;

//         int TURRET = 10;

//         int PRE_SHOOTER = 0/*TBD*/;

//         int SHOOTER_LEFT = 11;
//         int SHOOTER_RIGHT = 12;

//         int INDEXER_LEFT = 13;
//         int INDEXER_RIGHT = 14;

//         int CLIMBER_LEFT = 15;
//         int CLIMBER_RIGHT = 16;
        
//         int HOOD = 17;
//     }

//     /**
//      * put any sensor here and put encoders with the Encoder class
//      */
//     interface SENSOR {
//         float EXTERNAL_ENCODER_RESOLUTION = 4096.0f;
//         float INTERNAL_ENCODER_RESOLUTION = 2048.0f; 
//         Encoder FRONT_LEFT_ENCODER = new Encoder(8, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder FRONT_RIGHT_ENCODER = new Encoder(9, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder BACK_LEFT_ENCODER = new Encoder(10, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder BACK_RIGHT_ENCODER = new Encoder(11, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder LEFT_SHOOTER_ENCODER = new Encoder(4, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder RIGHT_SHOOTER_ENCODER = new Encoder(5, EXTERNAL_ENCODER_RESOLUTION);
//         Encoder HOOD_ENCODER = new Encoder(6, EXTERNAL_ENCODER_RESOLUTION);
//         SerialPort.Port GYRO_PORT = SerialPort.Port.kUSB;
//     }

//     /**
//      * Controllers and all button, axis, and other relavent info goes here
//      */
//     interface CONTROLLER {
//         interface XBOX {
//             int PORT = 0;
//             double THRESHOLD = 0.5d;
//             float DEBOUNCE_PERIOD = 0.5f;
//             double DEAD_ZONE_MAX = 0.1d;
//             double DEAD_ZONE_MIN = -0.1d;
//             interface BUTTON {
//                 int A = 1;
//                 int B = 2;
//                 int X = 3;
//                 int Y = 4;
//                 int LEFT_BUMPER = 5;
//                 int RIGHT_BUMPER = 5;
//                 int BACK = 7;
//                 int START = 8;
//                 int LEFT_JOYSTICK = 9;
//                 int RIGHT_JOYSTICK = 10;
//                 int RIGHT_TRIGGER = 11;
//                 int LEFT_TRIGGER = 12;
//             }
            
//             interface AXIS {
//                 int LEFT_X = 0;
//                 int LEFT_Y = 1;
//                 int RIGHT_X = 4;
//                 int RIGHT_Y = 5;
//             }

//             interface POV {
//                 int TOP = 11;
//                 int TOP_RIGHT = 12;
//                 int RIGHT = 13;
//                 int BOTTOM_RIGHT = 14;
//                 int BOTTOM = 15;
//                 int BOTTOM_LEFT = 16;
//                 int LEFT = 17;
//                 int TOP_LEFT = 18;
//                 int POV = 11;
//             }
//         }
//     }

//     /**
//      * any component that distrabutes example pdp
//      */
//     interface DISTRIBUTION {
//         int POWER = 0;
//     }

//     /**
//      * robot related things such as dimensions
//      * use Units class where can
//      */
//     interface ROBOT {
//         Units WHEEL_RADIUS = new Units(2, units.INCHES);   
//         double INTAKE_SPEED = 0.5d;
//         double FEEDER_SPEED = 0.5d;   
//         double PRE_SHOOTER_SPEED = 0.5d;   
//         double SHOOTER_SPEED = 0.5d;   
//     }

// } 




package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI.Port;
import frc.robot.subsystems.drivetrain.DesiredPosition;

public interface Constants {

    public interface SWERVE {
        double MAX_VOLTAGE = 12.0;
        double MAX_VELCOCITY = 6380.0/ 60* SdsModuleConfigurations.MK3_STANDARD.getDriveReduction() * SdsModuleConfigurations.MK3_STANDARD.getWheelDiameter();
        double MAX_ANGULAR = MAX_VELCOCITY / Math.hypot(ROBOT.TRACKWIDTH/2, ROBOT.WHEELBASE/2);
        Translation2d[] SWERVE_LOCATIONS = {ROBOT.FRONT_LEFT, ROBOT.FRONT_RIGHT, ROBOT.BACK_LEFT, ROBOT.BACK_RIGHT};
        int FRONT_LEFT_DRIVE = 0;
        int FRONT_LEFT_ROTATION = 4;
        int FRONT_LEFT_ENCODER = 8;
        
        int FRONT_RIGHT_DRIVE = 1;
        int FRONT_RIGHT_ROTATION = 5;
        int FRONT_RIGHT_ENCODER = 9;

        int BACK_LEFT_DRIVE = 2;
        int BACK_LEFT_ROTATION = 6;
        int BACK_LEFT_ENCODER = 10;

        int BACK_RIGHT_DRIVE = 3;
        int BACK_RIGHT_ROTATION = 7;
        int BACK_RIGHT_ENCODER = 11;

        double FRONT_LEFT_OFFSET = -Math.toRadians(0); //79.44488525390625 //309.375 //312
        double FRONT_RIGHT_OFFSET = -Math.toRadians(0); //211.102294921875 //15.0238037109375 //15
        double BACK_LEFT_OFFSET = -Math.toRadians(0);//335.56365966796875 //78.56597900390625 //79
        double BACK_RIGHT_OFFSET = -Math.toRadians(0);//155.126953125 //348.046875 //350
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

    public interface AUTO {
        frc.robot.utils.PID DEFUALT_DRIVE_PID = new frc.robot.utils.PID(1,0,0);
        frc.robot.utils.PID DEFUALT_ROTATION_PID = new frc.robot.utils.PID(0.01,0.001,0);
        DesiredPosition[] AUTO_1_POSITIONS = {new DesiredPosition(0,0,90)};
    }

    public interface ELEVATOR {
        int CLIMBER_LEFT = 15;
        int CLIMBER_RIGHT = 16;
    }

    public interface INTAKE {
        int LEFT = 0;
        int RIGHT = 0;
        int INTAKE_VELOCITY = 0;
    }

    public interface FEEDER {
        int FEEDER = 0/*TBD*/;
        int FEEDER_VELOCITY= 0;
    }

    public interface SHOOTER {
        int LEFT = 0;
        int RIGHT = 0;
        int LEFT_ENCODER = 0;
        int RIGHT_ENCODER = 0;
        int PRE = 0;
        int HIGH_VELOCITY = 0;
        int LOW_VELOCITY = 0;
        double TIMEOUT = 3;
        frc.robot.utils.PID TURRET_PID = new frc.robot.utils.PID(0,0,0);
        frc.robot.utils.PID SHOOTER_PID = new frc.robot.utils.PID(0,0,0);
    }    

    public interface TURRET {
        int ENCODER = 0;
        int TURRET = 0;
        int PRE = 0;
        int TURRET_MAX = 0;
        int TURRET_MIN = 0;
        double TIMEOUT = 3;
        frc.robot.utils.PID TURRET_PID = new frc.robot.utils.PID(0,0,0);
        frc.robot.utils.PID SHOOTER_PID = new frc.robot.utils.PID(0,0,0);
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
        double TRACKWIDTH = 0.47;
        double WHEELBASE = 0.47;
        Translation2d FRONT_LEFT = new Translation2d(TRACKWIDTH/2, WHEELBASE/2);
        Translation2d FRONT_RIGHT = new Translation2d(TRACKWIDTH/2, -WHEELBASE/2);
        Translation2d BACK_LEFT = new Translation2d(-TRACKWIDTH/2, WHEELBASE/2);
        Translation2d BACK_RIGHT = new Translation2d(-TRACKWIDTH/2, -WHEELBASE/2);
        SerialPort.Port GYRO_PORT = SerialPort.Port.kUSB;    
    }

    public interface PID {
        double DEFUALT_LIMIT = 0.05;
        double DEFUALT_THRESHOLD = 0.05;
    }
}