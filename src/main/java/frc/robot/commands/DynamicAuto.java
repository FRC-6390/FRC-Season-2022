package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;

public class DynamicAuto extends CommandBase {

    private DriveTrain driveTrain;
    private DesiredPosition cords[];
    private boolean done;

    public DynamicAuto(DriveTrain subsystem, DesiredPosition... cords) {
      driveTrain = subsystem;
      this.cords = cords;
    }
  
    @Override
    public void initialize() {
      done = false;
    }
  
    @Override
    public void execute() {
        for (int i = 0; i < cords.length; i++) {
            while(cords[i].threashhold()){
                /*
                  if(ball check){
                    follow ball
                  }else{

                  }
                */
                driveTrain.drive(cords[i].getChassisSpeeds(driveTrain.pos()));
            }
        }
        done = true;
    }
  
    @Override
    public void end(boolean interrupted) {}
  
    @Override
    public boolean isFinished() {
      return done;
    }
  }
  