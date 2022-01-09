package frc.robot.utils;

public class Encoder {
    int encoderId;
    float resolution;

    public Encoder(int encoderId, float resolution){
        this.encoderId = encoderId;
        this.resolution = resolution;
    }

    public int getEncoderId() {
        return encoderId;
    }

    public float getResolution() {
        return resolution;
    }
}