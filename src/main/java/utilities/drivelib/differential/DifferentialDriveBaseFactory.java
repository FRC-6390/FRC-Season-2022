package utilities.drivelib.differential;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class DifferentialDriveBaseFactory {

    public DifferentialDriveBaseFactory addMotorsLeft(CANSparkMax... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsLeft(PWMSparkMax... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsLeft(TalonFX... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsLeft(TalonSRX... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsLeft(VictorSP... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsLeft(VictorSPX... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(CANSparkMax... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(PWMSparkMax... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(TalonFX... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(TalonSRX... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(VictorSP... motors){
        return this;
    }

    public DifferentialDriveBaseFactory addMotorsRight(VictorSPX... motors){
        return this;
    }
}
