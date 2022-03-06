package frc.robot;

import java.util.Iterator;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.commands.IntakeAndFeederCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DesiredPositionFactory;
import frc.robot.subsystems.utils.swervelib.SdsModuleConfigurations;

public interface Constants {

    public interface SWERVE {
        double MAX_VOLTAGE = 12.0;
        double MAX_VELCOCITY = 6380.0/ 60* SdsModuleConfigurations.MK4_L1.getDriveReduction() * SdsModuleConfigurations.MK4_L1.getWheelDiameter();
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

        double FRONT_LEFT_OFFSET = -Math.toRadians(197.3968505859375);//309.814453125); //79.44488525390625 //309.375 //312
        double FRONT_RIGHT_OFFSET = -Math.toRadians(62.68524169921874);//242.04254150390622+180);//16.16363525390625); //211.102294921875 //15.0238037109375 //15 //373.963623046875
        double BACK_LEFT_OFFSET = -Math.toRadians(99.02801513671875);//80.0628662109375);//335.56365966796875 //78.56597900390625 //79 //79.00543212890625
        double BACK_RIGHT_OFFSET = -Math.toRadians(72.8668212890625);//251.0101318359375+180);//348.22265625);//155.126953125 //348.046875 //350
   
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
        int RIGHT_BUMPER = 6;
        int BACK = 7;
        int START = 8;
        int LEFT_JOYSTICK = 9;
        int RIGHT_JOYSTICK = 10;
        int RIGHT_TRIGGER = 3;
        int LEFT_TRIGGER = 2;
    
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
        frc.robot.subsystems.utils.PID DEFUALT_X_PID = new frc.robot.subsystems.utils.PID(0.5,0,0, 0, 0.05);
        frc.robot.subsystems.utils.PID DEFUALT_Y_PID = new frc.robot.subsystems.utils.PID(0.5,0,0, 0, 0.05);
        frc.robot.subsystems.utils.PID DEFUALT_ROTATION_PID = new frc.robot.subsystems.utils.PID(0.021,0.00,0,0,1);
        DesiredPosition[] AUTO_TEST_XY_POSITIONS = {new DesiredPosition(new Pose2d(1,2,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,2,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,1,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,2,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(3,2,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,2,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,3,Rotation2d.fromDegrees(0))), new DesiredPosition(new Pose2d(2,2,Rotation2d.fromDegrees(0)))};
        DesiredPosition[] AUTO_TEST_X = {new DesiredPosition(new Pose2d(3*1.25,2,Rotation2d.fromDegrees(0)))}; 
        DesiredPosition[] AUTO_TEST_ROTATION_POSITIONS = {new DesiredPosition(new Pose2d(2,2,Rotation2d.fromDegrees(90)))};
        Iterator<DesiredPosition> AUTO_FACTORY_ROTATION_POSITION = new DesiredPositionFactory(2,2,0).rotate(90).rotate(180).origin().build();
        Iterator<DesiredPosition> AUTO_FACTORY_XY_POSITION = new DesiredPositionFactory(2,2,0).to(2, 3).to(2,2).to(2, 1).to(2, 2).to(1, 2).to(2, 2).to(3, 2).to(2, 2).build();
        Iterator<DesiredPosition> AUTO_FACTORY_RELATIVE_XY_POSITION = new DesiredPositionFactory(2,2,0).relative(1, 0).relative(-1,0).relative(-1, 0).relative(1, 0).relative(0, 1).relative(0, -1).relative(0, 1).relative(0, -1).build();
        Iterator<DesiredPosition> AUTO_FACTORY_MIXED_XY_POSITION = new DesiredPositionFactory(2,2,0).relative(1, 0).origin().to(1, 2).origin().relative(0, 1).origin().to(2, 1).origin().build();
        Iterator<DesiredPosition> AUTO_1 = new DesiredPositionFactory(0,0,0).to(3, 0).build();
        // DesiredPosition[] AUTO_1_POSITIONS = {new DesiredPosition(1,0,0)};
    }

    public interface ELEVATOR {
        int LEFT = 15;
        int RIGHT = 16;
        int LEFT_SERVO = 0;
        int RIGHT_SERVO = 1;
        double UP_VELOCITY = 0.2;
        double DOWN_VELOCITY = 0.2;
        int TOP_LIMIT_SWITCH = 0;
        int BOTTOM_LIMIT_SWITCH = 1;
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

    public interface SHOOTER {
        int LEFT = 20;
        int RIGHT = 21;
        int TURRET = 17;
        int PRE_RIGHT = 22;
        int PRE_LEFT = 19;
        int LEFT_LIMIT_SWITCH = 2;
        int RIGHT_LIMIT_SWITCH = 3;

        double TURRET_MAX = 0;
        double TURRET_MIN = 0;
        double HIGH_VELOCITY = 30000;
        double LOW_VELOCITY = 12000;
        double TIMEOUT = 3;
        frc.robot.subsystems.utils.PID TURRET_PID = new frc.robot.subsystems.utils.PID(0.02,0,0,0,0.1);
        frc.robot.subsystems.utils.PID SHOOTER_PID = new frc.robot.subsystems.utils.PID(0.0003,0,0,0,1000);
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
        double TRACKWIDTH = 0.53;
        double WHEELBASE = 0.52;
        Translation2d FRONT_LEFT = new Translation2d(TRACKWIDTH/2, WHEELBASE/2);
        Translation2d FRONT_RIGHT = new Translation2d(TRACKWIDTH/2, -WHEELBASE/2);
        Translation2d BACK_LEFT = new Translation2d(-TRACKWIDTH/2, WHEELBASE/2);
        Translation2d BACK_RIGHT = new Translation2d(-TRACKWIDTH/2, -WHEELBASE/2);
        // intort GYRO_PORT = SerialPort.Port.kUSB;   
        int GYRO_PORT = 13; 
    }

    public interface PID {
        double DEFUALT_LIMIT = 0.05;
        double DEFUALT_THRESHOLD = 0.1;
    }
} 