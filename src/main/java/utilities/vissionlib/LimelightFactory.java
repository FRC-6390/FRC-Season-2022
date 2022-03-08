package utilities.vissionlib;

public class LimelightFactory {

    private static final String NETWORK_TABLE = "limelight";
    private Limelight kLimelight;
    
    public LimelightFactory(){
        this(NETWORK_TABLE);
    }

    public LimelightFactory(String table){
        kLimelight = new Limelight(table);
    }

    public LimelightFactory mountingAngle(double angle){
        kLimelight.setMountingAngle(angle);
        return this;
    }

    public LimelightFactory mountingHeight(double angle){
        kLimelight.setHeight(angle);
        return this;
    }

    public LimelightFactory goalHeight(double angle){
        kLimelight.setGoalHeight(angle);
        return this;
    }

    public Limelight build(){
        return kLimelight;
    }
}
