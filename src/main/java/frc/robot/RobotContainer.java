// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.IntakeProto;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  //public final DriveBase m_DriveBase = new DriveBase();

  

  //public final IntakePrototype m_intakePrototype = new IntakePrototype();

  //public DoubleSolenoid switchSol = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 0, 1);

  public final DriveBase m_DriveBase = new DriveBase();
  public final IntakeProto m_IntakeProto = new IntakeProto();
  public final XboxController driveJoy = new XboxController(0);
  public final XboxController opJoy = new XboxController(1);
  public final JoystickButton aButton = new JoystickButton(opJoy,1);
  public final JoystickButton bButton = new JoystickButton(opJoy,2);
  public final JoystickButton startButton = new JoystickButton(opJoy,8);
  String trajectoryJSON = "paths/YourPath.wpilib.json";
  public Command m_autonomousCommand;
  Trajectory trajectory = new Trajectory();
  public SendableChooser<String> autoChooser = new SendableChooser<String>();


  public double getDriveJoy(int axis) {
    double raw = driveJoy.getRawAxis(axis);
    return Math.abs(raw) < 0.1 ? 0.0 : raw;
  }
  public double getDriveJoyXR() {
    double raw = getDriveJoy(4);
    return raw;
    //return Math.abs(raw) < 0.1 ? 0.0 : raw > 0 ? (raw * raw) / 1.5 : (-raw * raw) / 1.5;
  }

  public double getDriveJoyYL() {
    double raw = getDriveJoy(1);
    return raw / 2;
    //return Math.abs(raw) < 0.1 ? 0.0 : raw > 0 ? (raw * raw) / 1.5 : (-raw * raw) / 1.5;
  }
  // The robot's subsystems and commands are defined here...
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    //old sparkmax code
    // Configure the trigger bindings
    //configureBindings();
  // }
  // public void teleopPeriodic(){
  
  //   m_DriveBase.m_drive.arcadeDrive(-getDriveJoyYL(), getDriveJoyXR());

  //   if(opJoy.getLeftBumper()){
  //     m_IntakeProto.pSpark.set(1.0);
  //   } else if(opJoy.getRightBumper()){
  //     m_IntakeProto.pSpark.set(-1.0);
  //   } else {
  //     m_IntakeProto.pSpark.setEncoderPos();
  //   }
  // }
  
  //new sparkmax code tried 1/28/23
  // link to github code used: https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Smart%20Motion%20Example/src/main/java/frc/robot/Robot.java
  double setPoint, processVariable;
  boolean mode = SmartDashboard.getBoolean("Mode", false);
  if(opJoy.getLeftBumper()) {
    setPoint = SmartDashboard.getNumber("Set Velocity", 1.0);
    m_IntakeProto.pSpark.set(1.0);
    processVariable = m_IntakeProto.pSpark.getSpeed();
  } else {
    setPoint = SmartDashboard.getNumber("Set Position", 0);
    /**
     * As with other PID modes, Smart Motion is set by calling the
     * setReference method on an existing pid object and setting
     * the control type to kSmartMotion
     */
    m_IntakeProto.pSpark.set(-1.0);
    processVariable = m_IntakeProto.pSpark.getPosition();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  // private void configureBindings() {
  //   // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  //   new Trigger(m_exampleSubsystem::exampleCondition)
  //       .onTrue(new ExampleCommand(m_exampleSubsystem));

  //   // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
  //   // cancelling on release.
  //   m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null; //Autos.exampleAuto(m_exampleSubsystem);
  }
  public void teleopPeriodic() {
  }
}
