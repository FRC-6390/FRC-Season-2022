package frc.robot.subsystems.utils.json;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import edu.wpi.first.wpilibj.Filesystem;

public class JsonManager {

    @SerializedName("desiredpositions")
    private List<JsonPosData> desiredpositions = null;

    public List<JsonPosData> getPositions() {
        return desiredpositions;
    }
    public void setPositions(List<JsonPosData> desiredpositions) {
        this.desiredpositions = desiredpositions;
    }

    public static String readFileAsString(String file)throws Exception{
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static InputStreamReader inputStream;
    public static List<JsonPosData> posList;
    public static List<Double> xList;
    public static List<Double> yList;
    public static List<Double> thetaList;
    public static List<Boolean> customPIDList;
    public static List<List<Double>> driveLists;
    public static List<List<Double>> rotationLists;

    
    // public static void main(String[] args) throws Exception {
    public void readJson(String autoName) throws Exception {

        //for testing purposes
        // String file = "C:\\Users\\Mohammad\\Documents\\GitHub\\FRC-Season-2022\\src\\main\\java\\frc\\robot\\utils\\json\\autos\\testAuto.json";
       
        String file = Filesystem.getDeployDirectory()+"/autos/"+ autoName + ".json";
        String json = readFileAsString(file);
        System.out.println(json);

        JsonManager manager = new Gson().fromJson(json, new TypeToken<JsonManager>() {}.getType());

        posList = manager.getPositions();
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        thetaList = new ArrayList<>();
        customPIDList = new ArrayList<>();
        driveLists = new ArrayList<List<Double>>();
        rotationLists = new ArrayList<List<Double>>();
        
        for (JsonPosData pos : posList) {
            xList.add(pos.getX());
            yList.add(pos.getY());
            thetaList.add(pos.getTheta());

            if(pos.getCustomPID() == true){

                for(JsonPidData pid : pos.getDrive()){
                    List<Double> drivePID = new ArrayList<>();
                    drivePID.add(pid.getP());
                    drivePID.add(pid.getI());
                    drivePID.add(pid.getD());
                    drivePID.add(pid.getILimit());
                    drivePID.add(pid.getThreshold());
                    driveLists.add(drivePID);
                    break;
                }
    
                for(JsonPidData pid : pos.getRotation()){
                    List<Double> rotationPID = new ArrayList<>();
                    rotationPID.add(pid.getP());
                    rotationPID.add(pid.getI());
                    rotationPID.add(pid.getD());
                    rotationPID.add(pid.getILimit());
                    rotationPID.add(pid.getThreshold());
                    rotationLists.add(rotationPID);
                    break;
                }
            } else{
                List<Double> drivePID = new ArrayList<>();
                driveLists.add(drivePID);

                List<Double> rotationPID = new ArrayList<>();
                rotationLists.add(rotationPID);
            }
        }

        System.out.println("Total Positions: " + posList.size());
        System.out.println(xList);
        System.out.println(yList);
        System.out.println(thetaList);
        System.out.println(driveLists);
        System.out.println(rotationLists);
    }
}