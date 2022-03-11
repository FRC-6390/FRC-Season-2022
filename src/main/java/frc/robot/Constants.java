package frc.robot;

import java.util.Iterator;

import edu.wpi.first.math.geometry.Translation2d;
import swervelib.SdsModuleConfigurations;
import utilities.controllib.pid.PID;
import utilities.controllib.pid.PIDFactory;
import utilities.desiredlib.DesiredPosition;
import utilities.desiredlib.DesiredPositionFactory;

public interface Constants {

    public interface SWERVE {
        double MAX_SPEED = 0.8;
        double MAX_VOLTAGE = 12.0;
        double MAX_VELCOCITY = 6380.0/ 60* SdsModuleConfigurations.MK4_L1.getDriveReduction() * SdsModuleConfigurations.MK4_L1.getWheelDiameter();
        double MAX_ANGULAR = MAX_VELCOCITY / Math.hypot(ROBOT.TRACKWIDTH/2, ROBOT.WHEELBASE/2);
        Translation2d[] SWERVE_LOCATIONS = {ROBOT.FRONT_LEFT, ROBOT.FRONT_RIGHT, ROBOT.BACK_LEFT, ROBOT.BACK_RIGHT};
        
        //Modules (Drive, Steer, Encoder)
        int[] FRONT_LEFT = {0,4,8};
        int[] FRONT_Right = {1,5,9};
        int[] BACK_LEFT = {2,6,10};
        int[] BACK_RIGHT = {3,7,11};

        double FRONT_LEFT_OFFSET = -Math.toRadians(197.3968505859375);
        double FRONT_RIGHT_OFFSET = -Math.toRadians(62.68524169921874);
        double BACK_LEFT_OFFSET = -Math.toRadians(99.02801513671875);
        double BACK_RIGHT_OFFSET = -Math.toRadians(72.8668212890625);

        PID DRIFT_CORRECTION_PID = new PIDFactory().p(0.07).d(0.004).build();
    }

    public interface AUTONOMOUS {
        PID DEFUALT_X_PID = new PIDFactory().p(0.5).build(); //0.05
        PID DEFUALT_Y_PID = new PIDFactory().p(0.5).build(); //0.05
        PID DEFUALT_ROTATION_PID = new PIDFactory().p(0.021).build(); //1
        Iterator<DesiredPosition> AUTO_1 = new DesiredPositionFactory(0,0,0).to(3, 0).build();
    }

    public interface ELEVATOR {
        int LEFT = 15;
        int RIGHT = 16;
        int LEFT_SERVO = 0;
        int RIGHT_SERVO = 1;
        double UP_VELOCITY = 0.2;
        double DOWN_VELOCITY = 0.2;
        int LIMIT_SWITCH = 1;
        int SERVO_MIN = 0;
        int SERVO_MAX = 70;
        int ENCODER = 12;
    }

    public interface INTAKE {
        int LEFT = 3;
        int RIGHT = 4;
    }

    public interface FEEDER {
        int FEEDER_MOTOR = 18;
        double VELOCITY = 0.7;
    }

    public interface TURRET {
        int LEFT = 20;
        int RIGHT = 21;
        int TURRET = 17;
        int PRE_RIGHT = 22;
        int PRE_LEFT = 19;
        int LEFT_LIMIT_SWITCH = 2;
        int RIGHT_LIMIT_SWITCH = 3;
        int ENCODER = 14;

        double TURRET_MAX = 0;
        double TURRET_MIN = 0;
        double HIGH_VELOCITY = 30000;
        double LOW_VELOCITY = 12000;
        double TURRET_TIMEOUT = 0.5;
        double SHOOTER_TIMEOUT = 1.5;
        double LOCK_THRESHOLD = 0.5;
        PID TURRET_PID = new PIDFactory().p(0.02).build();//0.1;
        PID SHOOTER_PID = new PIDFactory().p(0.0003).build();//1000;

        double LIMELIGHT_MOUNTING_ANGLE = 45;
        double LIMELIGHT_HEIGHT = 0;
        double LIMELIGHT_GOAL_HEIGHT = 0;
    }

    public interface ROBOT {
        int BLINKIN = 2;
        int PDH_PORT = 14;
        double TRACKWIDTH = 0.53;
        double WHEELBASE = 0.52;
        Translation2d FRONT_LEFT = new Translation2d(TRACKWIDTH/2, WHEELBASE/2);
        Translation2d FRONT_RIGHT = new Translation2d(TRACKWIDTH/2, -WHEELBASE/2);
        Translation2d BACK_LEFT = new Translation2d(-TRACKWIDTH/2, WHEELBASE/2);
        Translation2d BACK_RIGHT = new Translation2d(-TRACKWIDTH/2, -WHEELBASE/2);
        int GYRO_PORT = 13; 
    }
} 