// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.PS5Controller.Button;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OIConstants;
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
  private final Arm m_arm = new Arm();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  
  
  private final Joystick m_driveController = new Joystick(Constants.OIConstants.kDriverController);
  private final PS5Controller m_ps5 = new PS5Controller(Constants.OIConstants.kOtherController);
  
  private final Command m_autonomousCommand = new AutonomousCommand(m_drivetrain, m_arm, m_shooter, m_intake, m_ps5);
  
  private final DrivetrainSubsystem m_robotDrive = new DrivetrainSubsystem();


    PS5Controller m_driverController = new PS5Controller(OIConstants.kOtherController);

  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_drivetrain, m_arm, m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Amp Pos", new PositionAmp( m_arm, m_ps5 ));
    SmartDashboard.putData("Speaker Pos", new PositionSpeaker( m_arm, m_ps5 ));
    SmartDashboard.putData("Shoot Amp", new ShootAmpSequence(m_arm, m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Shoot Amp", new ShootAmpSequence(m_arm, m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Intake Pos", new PositionIntake( m_arm, m_ps5 ));
    
    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_arm);
    SmartDashboard.putData(m_intake);
    SmartDashboard.putData(m_shooter);

    // Configure the button bindings
    configureButtonBindings();
    

    
  }

  private void configureButtonBindings() {
    // set up the drivetrain command that runs all the time
        m_drivetrain.setDefaultCommand(new RunCommand( () -> m_drivetrain.driveArcade(m_driveController.getY(),-m_driveController.getZ()),m_drivetrain));

        
       // m_arm.setDefaultCommand(new RunCommand ( () -> m_arm.manualOverride(m_ps5.getLeftY())));     

        final JoystickButton shoot = new JoystickButton(m_driveController, 1);
        final POVButton shootSpeaker = new POVButton(m_ps5, 0);
        final POVButton shootAmp = new POVButton(m_ps5, 90);
        final POVButton prepareIntake = new POVButton(m_ps5, 180);
        final JoystickButton stopIt = new JoystickButton(m_ps5, 1);
        

        shoot.onTrue(new FeedShooter(m_intake));
        shootAmp.onTrue(new ShootAmpSequence(m_arm, m_shooter, m_intake, m_ps5));
        shootSpeaker.onTrue(new ShootSpeakerSequence(m_arm, m_shooter, m_intake, m_ps5));
        prepareIntake.onTrue(new PrepareIntake(m_arm, m_intake, m_ps5) );
        stopIt.onTrue(new StopIntakeAndShooter(m_shooter, m_intake));

 new JoystickButton(m_driverController, Button.kR1.value)
        .onTrue(new InstantCommand(() -> m_robotDrive.setMaxOutput(0.5)))
        .onFalse(new InstantCommand(() -> m_robotDrive.setMaxOutput(1)));

  new JoystickButton(m_driverController, Button.kL1.value)
  .whileTrue(
      new PIDCommand(
          new PIDController(
            Constants.kStabilizationP,
            Constants.kStabilizationI,
            Constants.kStabilizationD),
          // Close the loop on the turn rate
          m_robotDrive::getTurnRate,
          // Setpoint is 0
          0,
          // Pipe the output to the turning controls
          output -> m_robotDrive.arcadeDrive(-m_driverController.getLeftY(), output),
          // Require the robot drive
          m_robotDrive));
        
        }



  


  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}