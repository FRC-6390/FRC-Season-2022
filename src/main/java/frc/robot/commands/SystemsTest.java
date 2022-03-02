// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TurretedShooter;

public class SystemsTest extends CommandBase {
  TalonFX m0, m1, m2, m3, m4, m5, m6, m7, m0_7[];
  CANSparkMax m15, m16, m17, m18, m19, m20, m21, m22, m15_22[];
  PWMSparkMax pwm3, pwm4, pwm3_4[];
  double speed = 0.3;
  boolean done = false, start = false;
  public SystemsTest() {}

  @Override
  public void initialize() {
    m0 = new TalonFX(0, "Swerve CANivore");
    m1 = new TalonFX(1, "Swerve CANivore");
    m2 = new TalonFX(2, "Swerve CANivore");
    m3 = new TalonFX(3, "Swerve CANivore");
    m4 = new TalonFX(4, "Swerve CANivore");
    m5 = new TalonFX(5, "Swerve CANivore");
    m6 = new TalonFX(6, "Swerve CANivore");
    m7 = new TalonFX(7, "Swerve CANivore");

    m15 = Elevator.left;
    m16 = Elevator.right;
    m17 = Feeder.feederMotor;
    m18 = TurretedShooter.turret;
    m19 = TurretedShooter.preLeftShooter;
    m20 = TurretedShooter.shooterLeft;
    m21 = TurretedShooter.shooterRight;
    m22 = TurretedShooter.preRightShooter;

    pwm3 = Intake.left;
    pwm4 = Intake.right;

    m16.setInverted(true);

    m0_7 = new TalonFX[]{m0,m1,m2,m3,m4,m5,m6,m7};
    m15_22 = new CANSparkMax[]{m15,m16,m17,m18,m19,m20,m21,m22};
    pwm3_4 = new PWMSparkMax[] {pwm3, pwm4};
    done = false;
    start = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.y.debounced()) start = true;
    if(start){
      for (int i = 0; i < m0_7.length;) {
        m0_7[i].set(ControlMode.PercentOutput, speed);
        if(RobotContainer.x.debounced()) speed = -speed;
        if(RobotContainer.y.debounced()){
            m0_7[i].set(ControlMode.PercentOutput, 0.0);
           i++;
           speed = Math.abs(speed);
        }
        // if(RobotContainer.0)
      }
  
      for (int i = 0; i < m15_22.length;) {
        m15_22[i].set(speed);
        if(RobotContainer.x.debounced()) speed = -speed;
        if(RobotContainer.y.debounced()) {
          m15_22[i].set(0.0);
          i++;
        }
      }
  
      for (int i = 0; i < pwm3_4.length;) {
        pwm3_4[i].set(speed);
        if(RobotContainer.x.debounced()) speed = -speed;
        if(RobotContainer.y.debounced()){
          pwm3_4[i].set(0.0);
           i++;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    for (int i = 0; i < m0_7.length;) {
      m0_7[i].set(ControlMode.PercentOutput, 0.0);
    }
    for (int i = 0; i < m15_22.length;) {
      m15_22[i].set(0.0);
    }
    for (int i = 0; i < pwm3_4.length;) {
      pwm3_4[i].set(0.0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
