// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {
  public CANSparkMax _left1 = new CANSparkMax(4, MotorType.kBrushless);
  public CANSparkMax _left2 = new CANSparkMax(3, MotorType.kBrushless);

  public CANSparkMax _right1 = new CANSparkMax(2, MotorType.kBrushless);
  public CANSparkMax _right2 = new CANSparkMax(5, MotorType.kBrushless);

  public DifferentialDrive m_drive = new DifferentialDrive(_left1, _right1);
  /** Creates a new DriveBase. */
  public DriveBase() {
    _left2.follow(_left1);
    _right2.follow(_right1);

    //Inverts left motors from standard

    _left1.setInverted(true);
    _left2.setInverted(true);

   _right1.setInverted(false);
   _right2.setInverted(false);
  

    //Sets motors to Brake mode!!!!!!!!
    //Eady is a poopy stupid head

    _left1.setIdleMode(IdleMode.kBrake);
    _left2.setIdleMode(IdleMode.kBrake);

    _right1.setIdleMode(IdleMode.kBrake);
    _right2.setIdleMode(IdleMode.kBrake);



  }
  //Creates Leader-Follower relationship between motors 




  //Sets motors to Brake mode!!!!!!!!
  //Eady is a poopy stupid head



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}


