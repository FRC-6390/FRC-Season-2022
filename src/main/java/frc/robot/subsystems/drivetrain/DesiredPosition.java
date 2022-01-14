package frc.robot.subsystems.drivetrain;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class DesiredPosition {
    public Pose2d desiredCord;
    public List<DesiredSettings> settings;
    public DesiredPID drivePid, rotationPid;
    public DesiredSpeeds desiredSpeeds;

    public DesiredPosition(Pose2d desiredCord, DesiredSettings... settings){
        this(desiredCord, new DesiredPID(false), new DesiredPID(true), settings);
    }

    public DesiredPosition(Pose2d desiredCord, DesiredPID drivePid, DesiredPID rotationPid, DesiredSettings... settings){
        this.desiredCord = desiredCord;
        this.drivePid = drivePid;
        this.rotationPid = rotationPid;
        this.settings = Arrays.asList(settings);
        this.desiredSpeeds = new DesiredSpeeds();
    }

    public DesiredSpeeds getDesiredSpeeds(Pose2d currentPosition){

        desiredSpeeds.xDistance = settings.contains(DesiredSettings.Ignore_Drive) == true ? 0.0 :calculateDistance(currentPosition.getX(), desiredCord.getX());
        desiredSpeeds.yDistance = settings.contains(DesiredSettings.Ignore_Drive) == true ? 0.0 :calculateDistance(currentPosition.getY(), desiredCord.getY());
        desiredSpeeds.thetaDistance = settings.contains(DesiredSettings.Ignore_Rotation) == true ? 0.0 : calculateDistance(currentPosition.getRotation().getDegrees(), desiredCord.getRotation().getDegrees());

        if(settings.contains(DesiredSettings.Point_To_Heading)){
            desiredCord = pointToHeading(currentPosition);
        }

        desiredSpeeds.x = calculateX(desiredSpeeds);
        desiredSpeeds.y = calculateY(desiredSpeeds);
        desiredSpeeds.theta = calculateTheta(desiredSpeeds);
    
        
        return desiredSpeeds; 
    }

    public double calculateX(DesiredSpeeds speeds){

        if(settings.contains(DesiredSettings.Ignore_Drive))
            return 0.0;

        if(settings.contains(DesiredSettings.Rotate_Then_Drive)){
            if(!rotationPid.underThreshold(speeds.thetaDistance)){
                return 0.0;
            }
        }

        if(settings.contains(DesiredSettings.Y_Then_X)){
            if(!speeds.yDone){
                return 0.0;
            }
        }

        if(drivePid.underThreshold(speeds.xDistance)){
            speeds.xDone = true;
        }

        return drivePid.calculateSpeed(speeds.xDistance);
    }

    public double calculateY(DesiredSpeeds speeds){

        if(settings.contains(DesiredSettings.Ignore_Drive))
            return 0.0;

        if(settings.contains(DesiredSettings.Rotate_Then_Drive)){
            if(!rotationPid.underThreshold(speeds.thetaDistance)){
                return 0.0;
            }
        }

        if(settings.contains(DesiredSettings.X_Then_Y)){
            if(!speeds.xDone){
                return 0.0;
            }
        }

        if(drivePid.underThreshold(speeds.yDistance)){
            speeds.yDone = true;
        }

        return drivePid.calculateSpeed(speeds.yDistance);
    }

    public double calculateTheta(DesiredSpeeds speeds){

        if(settings.contains(DesiredSettings.Ignore_Rotation)){
            return 0.0;
        }

        if(settings.contains(DesiredSettings.Drive_Then_Rotate)){
                if(!(drivePid.underThreshold(speeds.xDistance) && drivePid.underThreshold(speeds.yDistance))){
                   return 0.0;
                }
        }

        if(rotationPid.underThreshold(speeds.thetaDistance)){
            speeds.thetaDone = true;
        }

        return rotationPid.calculateSpeed(speeds.thetaDistance);
    }

    public Pose2d pointToHeading(Pose2d currentPosition){
      
        double angle = getAngle(currentPosition);
        System.out.println(angle);
        if(desiredCord.getRotation().getDegrees() == angle)
            return desiredCord;

        return new Pose2d(desiredCord.getX(), desiredCord.getY(), Rotation2d.fromDegrees(angle));
    }

    //https://stackoverflow.com/a/9970297
    public double getAngle(Pose2d curerntPosition) {
        double angle = (float) Math.toDegrees(Math.atan2(desiredCord.getY() - curerntPosition.getY(), desiredCord.getX() - curerntPosition.getX()));
        return (angle < 0) ? (angle+360) : angle;
    }

    public double calculateDistance(double currentLocation, double desiredLocation){
        return desiredLocation - currentLocation;
    }

    public boolean atDesiredPosition(){
        return drivePid.underThreshold(desiredSpeeds.xDistance) && drivePid.underThreshold(desiredSpeeds.yDistance) && rotationPid.underThreshold(desiredSpeeds.thetaDistance);
    }

    public static DesiredPosition fromCords(double x, double y, double theta, DesiredSettings... settings){
        return fromCords(x,y,theta, new DesiredPID(false), new DesiredPID(true), settings);
    }

    public static DesiredPosition fromCords(double x, double y, double theta, DesiredPID drivePid, DesiredPID rotationPid, DesiredSettings... settings){
        return new DesiredPosition(new Pose2d(x,y,Rotation2d.fromDegrees(theta)), drivePid, rotationPid, settings);
    }

    public static enum DesiredSettings{
        Point_To_Heading, Ignore_Rotation, Ignore_Drive, Rotate_Then_Drive, Drive_Then_Rotate, X_Then_Y, Y_Then_X;
    }


    public static class DesiredSpeeds{

        public double x, y, theta;
        public double xDistance, yDistance, thetaDistance;
        public boolean xDone, yDone, thetaDone;

        public DesiredSpeeds(){
            this.x = 0;
            this.y = 0;
            this.theta = 0;
        }
    }

    public static class DesiredPID{
        public double p, i, d, iLimit, threshold;
        public double prevError = 0;
        public double errorRate = 0;
        public double sum = 0;
        public double timeStamp = Timer.getFPGATimestamp();

        public DesiredPID(boolean rotation){
            if(rotation){
                this.p = DEFAULT.ROTATION.P.get();
                this.i = DEFAULT.ROTATION.I.get();
                this.d = DEFAULT.ROTATION.D.get();
                this.iLimit = DEFAULT.ROTATION.I_LIMIT.get();
                this.threshold = DEFAULT.ROTATION.THRESHOLD.get();
            }else{
                this.p = DEFAULT.DRIVE.P.get();
                this.i = DEFAULT.DRIVE.I.get();
                this.d = DEFAULT.DRIVE.D.get();
                this.iLimit = DEFAULT.DRIVE.I_LIMIT.get();
                this.threshold = DEFAULT.DRIVE.THRESHOLD.get();
            }
                
        }

        public DesiredPID(double p, double i, double d, double iLimit, double threshold){
            this.p = p;
            this.i = i;
            this.d = d;
            this.iLimit = iLimit;
            this.threshold = threshold;
        }

        public boolean underThreshold(double error){
            return Math.abs(error) < threshold;
        }

        public double calculateSpeed(double error){
            double dt = Timer.getFPGATimestamp() - timeStamp;
            if(Math.abs(error) < iLimit) sum += error * dt;
            timeStamp = Timer.getFPGATimestamp();
            prevError = error;
            double output = (p * error) + (i * sum) + (d * (error - prevError) / dt);
            return Math.abs(output) < DEFAULT.SPEED_LIMIT ? output : output > 0 ? DEFAULT.SPEED_LIMIT : -DEFAULT.SPEED_LIMIT;
        }

        public static DesiredPID fromValues(double p, double i, double d, double iLimit, double threshold){
            return new DesiredPID(p, i, d, iLimit, threshold);
        }
        
    }

    private static interface DEFAULT {

        double SPEED_LIMIT = 0.03;
        
        enum DRIVE{
            P(0.075),
            I(0.1),
            D(0.0),
            I_LIMIT(0.25),
            THRESHOLD(0.025);

            private double val;
            private DRIVE(double val){
                this.val = val;
            }

            public double get(){
                return val;
            }
        }

        enum ROTATION{
            P(0.0015),
            I(0.007),
            D(0.0),
            I_LIMIT(10.0),
            THRESHOLD(0.25);

            private double val;

            private ROTATION(double val){
                this.val = val;
            }

            public double get(){
                return val;
            }
        }
        
    }
}
