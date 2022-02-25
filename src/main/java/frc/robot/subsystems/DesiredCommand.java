package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class DesiredCommand {
    
    Command command;

    public DesiredCommand(Command command){
        this.command = command;
    }

    public void run(){
       if(!command.isScheduled()) command.schedule();
    }

    public void cancel(){
        if(command.isScheduled()) command.cancel();
     }

    public boolean isDone(){
        return command.isFinished();
    }
    
}
