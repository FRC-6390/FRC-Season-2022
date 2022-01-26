package frc.robot;

import frc.robot.utils.Units.units;
import frc.robot.utils.Units;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.utils.Encoder;
import frc.robot.utils.PID;
import frc.robot.utils.Module;

public interface Constants {

    //Pleases only put constants in one section and link it to others if neeeded, if you feel none of the sections below fit
    //what you are trying to add, create a new interface with the name of the section your constant falls under, be as general as possible
    //NOTE all variables and interface names must be CAPS with variables having "_" for spaces and interfaces having no "_"

    /**
     * put any sweremodules here with the Module class
     */
    interface SWERVE {
        Module FRONT_LEFT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
        Module FRONT_RIGHT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
        Module BACK_LEFT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);
        Module BACK_RIGHT = new Module(SENSOR.FRONT_LEFT_ENCODER, MOTOR.FRONT_LEFT_DRIVE, MOTOR.FRONT_LEFT_ROTATION);

        Units MAX_SPEED = new Units(0.3, units.METERS);
        double PRECENT_MAX_SPEED = 0.1;
        
        double FRONT_LEFT_OFFSET = 230.0;
        double FRONT_RIGHT_OFFSET = 164.0;
        double BACK_LEFT_OFFSET = 103.0;
        double BACK_RIGHT_OFFSET = 193.0;

        double DRIVE_GEAR_RATIO = 8.16;     //6.12
        double ROTATION_GEAR_RATIO = 12.8; 

        PID DRIVE_PID = new PID(0, 0, 0, 0);
        PID ROTATION_PID = new PID(1, 0, 0, 0);

        Units LOCATION_FROM_CENTER = new Units(0.3302, units.METERS);
    }
    
    /**
     * Put any motors here
     */
    interface MOTOR {
        int FRONT_LEFT_DRIVE = 0;
        int FRONT_RIGHT_DRIVE = 1;
        int BACK_LEFT_DRIVE = 2;
        int BACK_RIGHT_DRIVE = 3;

        int FRONT_LEFT_ROTATION = 4;
        int FRONT_RIGHT_ROTATION = 5;
        int BACK_LEFT_ROTATION = 6;
        int BACK_RIGHT_ROTATION = 7;

        int INTAKE_LEFT = 8;
        int INTAKE_RIGHT = 9;

        int FEEDER = 0/*TBD*/;

        int TURRET = 10;

        int PRE_SHOOTER = 0/*TBD*/;

        int SHOOTER_LEFT = 11;
        int SHOOTER_RIGHT = 12;

        int INDEXER_LEFT = 13;
        int INDEXER_RIGHT = 14;

        int CLIMBER_LEFT = 15;
        int CLIMBER_RIGHT = 16;
        
        int HOOD = 17;
    }

    /**
     * put any sensor here and put encoders with the Encoder class
     */
    interface SENSOR {
        float EXTERNAL_ENCODER_RESOLUTION = 4096.0f;
        float INTERNAL_ENCODER_RESOLUTION = 2048.0f; 
        Encoder FRONT_LEFT_ENCODER = new Encoder(0, EXTERNAL_ENCODER_RESOLUTION);
        Encoder FRONT_RIGHT_ENCODER = new Encoder(1, EXTERNAL_ENCODER_RESOLUTION);
        Encoder BACK_LEFT_ENCODER = new Encoder(2, EXTERNAL_ENCODER_RESOLUTION);
        Encoder BACK_RIGHT_ENCODER = new Encoder(3, EXTERNAL_ENCODER_RESOLUTION);
        int FRONT_LEFT_LIMIT = 0;
        int FRONT_RIGHT_LIMIT = 0;
        int BACK_LEFT_LIMIT = 0;
        int BACK_RIGHT_LIMIT = 0;
        Encoder SHOOTER_ENCODER = new Encoder(4, EXTERNAL_ENCODER_RESOLUTION);
        Encoder HOOD_ENCODER = new Encoder(5, EXTERNAL_ENCODER_RESOLUTION);
        SerialPort.Port GYRO_PORT = SerialPort.Port.kUSB;
    }

    /**
     * Controllers and all button, axis, and other relavent info goes here
     */
    interface CONTROLLER {
        interface XBOX {
            int PORT = 0;
            double THRESHOLD = 0.5d;
            float DEBOUNCE_PERIOD = 0.5f;
            double DEAD_ZONE_MAX = 0.15d;
            double DEAD_ZONE_MIN = -0.15d;
            interface BUTTON {
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
            }
            
            interface AXIS {
                int LEFT_X = 0;
                int LEFT_Y = 1;
                int RIGHT_X = 4;
                int RIGHT_Y = 5;
            }

            interface POV {
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
        }
    }

    /**
     * any component that distrabutes example pdp
     */
    interface DISTRIBUTION {
        int POWER = 0;
    }

    /**
     * robot related things such as dimensions
     * use Units class where can
     */
    interface ROBOT {
        Units WHEEL_RADIUS = new Units(2, units.INCHES);   
        double INTAKE_SPEED = 0.5d;
        double FEEDER_SPEED = 0.5d;   
        double PRE_SHOOTER_SPEED = 0.5d;   
        double SHOOTER_SPEED = 0.5d;   
    }

} 