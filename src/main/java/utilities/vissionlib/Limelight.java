package utilities.vissionlib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private static final String NETWORK_TABLE = "limelight";
    private NetworkTable kNetworkTable;
    private NetworkTableEntry kTV, kTX, kTY, kTA, kTS, kTL, kTShort, kTLong, kTHor, kTVert, kGetPipe, kCamTran, kTC, 
                              kLedMode, kCamMode, kPipeline, kStream, kSnapshot,
                              kLLPython, kLLRobot,
                              kTCornXY,
                              kTY0, kTX0, kTA0, kTS0, kTX1, kTY1, kTA1, kTS1, kTX2, kTY2, kTA2, kTS2,                              
                              kCX0, kCY0, kCX1, kCY1;

    private double kAngle, kHeight, kGoalHeight;

    public static enum LedMode {
        Pipeline(0),
        Off(1),
        Blink(2),
        On(3);
        int val;
        private LedMode(int val){
            this.val = val;
        }
    }

    public static enum CamMode {
        VisionProcessor(0),
        DriverCamera(1);
        int val;
        private CamMode(int val){
            this.val = val;
        }
    }

    public static enum StreamMode {
        Standard(0),
        PiPMain(1),
        PiPSecondary(2);
        int val;
        private StreamMode(int val){
            this.val = val;
        }
    }

    public static enum SnapshotMode {
        Stop(0),
        Start(1);
        int val;
        private SnapshotMode(int val){
            this.val = val;
        }
    }

    public Limelight(){
        this(NETWORK_TABLE);
    }
    
    public Limelight(String table){
        kNetworkTable = NetworkTableInstance.getDefault().getTable(table);
        kTV = kNetworkTable.getEntry("tv");
        kTX = kNetworkTable.getEntry("tx");
        kTY = kNetworkTable.getEntry("ty");
        kTA = kNetworkTable.getEntry("ta");
        kTS = kNetworkTable.getEntry("ts");
        kTL = kNetworkTable.getEntry("tl");
        kTShort = kNetworkTable.getEntry("tshort");
        kTLong = kNetworkTable.getEntry("tlong");
        kTHor = kNetworkTable.getEntry("thor");
        kTVert = kNetworkTable.getEntry("tvert");
        kGetPipe = kNetworkTable.getEntry("getpipe");
        kCamTran = kNetworkTable.getEntry("camtran");
        kTC = kNetworkTable.getEntry("tc");
        kLedMode = kNetworkTable.getEntry("ledMode");
        kCamMode = kNetworkTable.getEntry("camMode");
        kPipeline = kNetworkTable.getEntry("pipeline");
        kStream = kNetworkTable.getEntry("stream");
        kSnapshot = kNetworkTable.getEntry("snapshot");
        kLLPython = kNetworkTable.getEntry("llpython");
        kLLRobot = kNetworkTable.getEntry("llrobot");
        kTCornXY = kNetworkTable.getEntry("tcornxy");
        kTY0 = kNetworkTable.getEntry("ty0");
        kTX0 = kNetworkTable.getEntry("tx0");
        kTA0 = kNetworkTable.getEntry("ta0");
        kTS0 = kNetworkTable.getEntry("ts0");
        kTX1 = kNetworkTable.getEntry("tx1");
        kTY1 = kNetworkTable.getEntry("ty1");
        kTA1 = kNetworkTable.getEntry("ta1");
        kTS1 = kNetworkTable.getEntry("ts1");
        kTX2 = kNetworkTable.getEntry("tx2");
        kTY2 = kNetworkTable.getEntry("ty2");
        kTA2 = kNetworkTable.getEntry("ta2");
        kTS2 = kNetworkTable.getEntry("ts2");
        kCX0 = kNetworkTable.getEntry("cx0");
        kCY0 = kNetworkTable.getEntry("cy0");
        kCX1 = kNetworkTable.getEntry("cx1");
        kCY1 = kNetworkTable.getEntry("cy1");
    }

    public NetworkTable getNetworkTable() {
        return kNetworkTable;
    }

    /**
     * Whether the limelight has any valid targets (0 or 1)
     * @return
     */
    public double tv() {
        return kTV.getDouble(0.0);
    }

    /**
     * Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
     * @return
     */
    public double tx() {
        return kTX.getDouble(0.0);
    }

    /**
     * Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
     * @return
     */
    public double ty() {
        return kTY.getDouble(0.0);
    }
    /**
     * Target Area (0% of image to 100% of image)
     * @return
     */
    public double ta() {
        return kTA.getDouble(0.0);
    }
    /**
     * Skew or rotation (-90 degrees to 0 degrees)
     * @return
     */
    public double ts() {
        return kTS.getDouble(0.0);
    }
    /**
     * The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
     * @return
     */
    public double tl() {
        return kTL.getDouble(0.0);
    }
    /**
     * Sidelength of shortest side of the fitted bounding box (pixels)
     * @return
     */
    public double tshort() {
        return kTShort.getDouble(0.0);
    }
    /**
     * Sidelength of longest side of the fitted bounding box (pixels)
     * @return
     */
    public double tlong() {
        return kTLong.getDouble(0.0);
    }
    /**
     * Horizontal sidelength of the rough bounding box (0 - 320 pixels)
     * @return
     */
    public double thor() {
        return kTHor.getDouble(0.0);
    }
    /**
     * Vertical sidelength of the rough bounding box (0 - 320 pixels)
     * @return
     */
    public double tvert() {
        return kTVert.getDouble(0.0);
    }
    /**
     * True active pipeline index of the camera (0 .. 9)
     * @return
     */
    public double getpipe() {
        return kGetPipe.getDouble(0.0);
    }
    /**
     * Results of a 3D position solution, NumberArray: Translation (x,y,z) Rotation(pitch,yaw,roll)
     * @return
     */
    public double camtran() {
        return kCamTran.getDouble(0.0);
    }
    /**
     * Get the average HSV color underneath the crosshair region as a NumberArray
     * @return
     */
    public Number[] tc() {
        return kTC.getNumberArray(null);
    }
    /**
     * Sets limelight’s LED state
     * @return
     */
    public void setLedMode(LedMode mode){
        kLedMode.setNumber(mode.val);
    }
    /**
     * Sets limelight’s operation mode
     * @return
     */
    public void setCamMode(CamMode mode){
        kCamMode.setNumber(mode.val);
    }
    /**
     * Sets limelight’s current pipeline (0-9)
     * @return
     */
    public void setPipeline(int mode){
        kPipeline.setNumber(mode);
    }

    /**
     * Sets limelight’s streaming mode
     * @return
     */
    public void setStream(StreamMode mode){
        kStream.setNumber(mode.val);
    }

    /**
     * Allows users to take snapshots during a match
     * @return
     */
    public void setSnapshot(SnapshotMode mode){
        kSnapshot.setNumber(mode.val);
    }
    /**
     * NumberArray sent by python scripts. This is accessible from robot code
     * @return
     */
    public Number[] llpython(){
        return kLLPython.getNumberArray(null);
    }
    /**
     * NumberArray sent by the robot. This is accessible from python scripts
     * @return
     */
    public Number[] llrobot(){
        return kLLRobot.getNumberArray(null);
    }
    /**
     * Number array of corner coordinates [x0,y0,x1,y1……]
     * @return
     */
    public Number[] tcornxy(){
        return kTCornXY.getNumberArray(null);
    }
    /**
     * Raw Screenspace X
     * @return
     */
    public double tx0() {
        return kTX0.getDouble(0.0);
    }
    /**
     * Raw Screenspace Y
     * @return
     */
    public double ty0() {
        return kTY0.getDouble(0.0);
    }
    /**
     * Area (0% of image to 100% of image)
     * @return
     */
    public double ta0() {
        return kTA0.getDouble(0.0);
    }
    /**
     * Skew or rotation (-90 degrees to 0 degrees)
     * @return
     */
    public double ts0() {
        return kTS0.getDouble(0.0);
    }
    /**
     * Raw Screenspace X
     * @return
     */
    public double tx1() {
        return kTX1.getDouble(0.0);
    }
    /**
     * Raw Screenspace Y
     * @return
     */
    public double ty1() {
        return kTY1.getDouble(0.0);
    }
    /**
     * Area (0% of image to 100% of image)
     * @return
     */
    public double ta1() {
        return kTA1.getDouble(0.0);
    }
    /**
     * Skew or rotation (-90 degrees to 0 degrees)
     * @return
     */
    public double ts1() {
        return kTS1.getDouble(0.0);
    }
    /**
     * Raw Screenspace X
     * @return
     */
    public double tx2() {
        return kTX2.getDouble(0.0);
    }
    /**
     * Raw Screenspace Y
     * @return
     */
    public double ty2() {
        return kTY2.getDouble(0.0);
    }
    /**
     * Area (0% of image to 100% of image)
     * @return
     */
    public double ta2() {
        return kTA2.getDouble(0.0);
    }
    /**
     * Skew or rotation (-90 degrees to 0 degrees)
     * @return
     */
    public double ts2() {
        return kTS2.getDouble(0.0);
    }
    /**
     * Crosshair A X in normalized screen space
     * @return
     */
    public double cx0(){
        return kCX0.getDouble(0.0);
    }
    /**
     * Crosshair A Y in normalized screen space
     * @return
     */
    public double cy0(){
        return kCY0.getDouble(0.0);
    }
    /**
     * Crosshair B X in normalized screen space
     * @return
     */
    public double cx1(){
        return kCX1.getDouble(0.0);
    }
    /**
     * Crosshair B Y in normalized screen space
     * @return
     */
    public double cy1(){
        return kCY1.getDouble(0.0);
    }

    /**
     * Distance from limelight to goal in inches
     * @return
     */
    public double distance(){
        return (kGoalHeight - kHeight)/Math.tan((kAngle + ty()) * (Math.PI / 180.0));
    }

    /**
     * How many degrees back is your limelight rotated from perfectly vertical
     * @return
     */
    public void setMountingAngle(double angle){
        kAngle = angle;
    }

    /**
     * Distance from the center of the Limelight lens to the floor
     * @return
     */
    public void setHeight(double height){
        kHeight = height;
    }

    /**
     * Distance from the target to the floor
     * @return
     */
    public void setGoalHeight(double height){
        kGoalHeight = height;
    }
}

