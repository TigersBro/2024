// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  // The robot's subsystems and commands are defined here...
 

  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  
  
  private final Joystick m_driveController = new Joystick(Constants.OIConstants.kDriverController);
  private final PS5Controller m_ps5 = new PS5Controller(Constants.OIConstants.kOtherController);
  
  private final Command m_autonomousCommand = new AutonomousCommand(m_drivetrain,  m_shooter, m_intake, m_ps5);
  
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    CameraServer.startAutomaticCapture();

    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_drivetrain, m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Shoot Amp", new ShootAmpSequence( m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Shoot Amp", new ShootAmpSequence( m_shooter, m_intake, m_ps5));
    
    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_intake);
    SmartDashboard.putData(m_shooter);

    // Configure the button bindings
    configureButtonBindings();
    
    
    
  }

  private void configureButtonBindings() {
    // set up the drivetrain command that runs all the time
        m_drivetrain.setDefaultCommand(new RunCommand( () -> m_drivetrain.arcadeDrive(m_driveController.getY(),-m_driveController.getZ()),m_drivetrain));

        final JoystickButton shoot = new JoystickButton(m_driveController, 1);
       // final POVButton shootSpeaker = new POVButton(m_ps5, 0);
       // final POVButton shootAmp = new POVButton(m_ps5, 90);
       // final POVButton prepareIntake = new POVButton(m_ps5, 180);
       // final JoystickButton backupFeed = new JoystickButton(m_ps5, 3);
       final JoystickButton stopIt = new JoystickButton(m_ps5, 2);
       final JoystickButton suckUp = new JoystickButton(m_ps5, 1);
       final JoystickButton shootPS5Button= new JoystickButton(m_ps5, 8);
       
        shoot.onTrue(new StartLaunch(m_shooter,m_ps5) );
shootPS5Button.onTrue(new StartLaunch(m_shooter,m_ps5));
        stopIt.onTrue(new StopIntakeAndShooter(m_shooter, m_intake));
        suckUp.onTrue(new StartIntake(m_intake,m_ps5));


  }


  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}