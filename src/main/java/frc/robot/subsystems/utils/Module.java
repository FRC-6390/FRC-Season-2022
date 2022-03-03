package frc.robot.subsystems.utils;

import edu.wpi.first.math.geometry.Rotation2d;

public class Module {

    public int drive, rotation, encoder;
    public Rotation2d offset;
    
    public Module(int drive, int rotation, int encoder, Rotation2d offset){
        this.drive = drive;
        this.rotation = rotation;
        this.encoder = encoder;
        this.offset = offset;
    }
}
