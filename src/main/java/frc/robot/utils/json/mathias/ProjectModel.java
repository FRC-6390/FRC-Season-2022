package frc.robot.utils.json.mathias;

public class ProjectModel {
    

    public String name;
    public TabModel[] tabs;
    
    public static class TabModel {
        public int index;
        public String name;
        public DesiredPositionModel[] desiredpositions;
    }

    public static class DesiredPositionModel {

        public int index;
        public double x;
        public double y;
        public double theta;
        public String settings[];
        public boolean customPID;
        public PIDModel drivePID;
        public PIDModel rotationPID;
    }
    
    public static class PIDModel {
            public boolean rotation;
            public double p = 0.0;
            public double i = 0.0;
            public double d = 0.0;
            public double ilimit = 0.0;
            public double threshold = 0.0;
    }
    
    
}
