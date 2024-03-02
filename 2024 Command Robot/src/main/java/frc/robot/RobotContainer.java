// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
  private final Joystick m_driveController = new Joystick(Constants.OIConstants.kDriverController); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings(); 
  }


  private void configureButtonBindings() {
    //set up the drivetrain command that runs all the time
    m_drivetrain.setDefaultCommand(new RunCommand(
      () -> 
        m_drivetrain.driveArcade(
          MathUtil.applyDeadband(m_driveController.getY(), Constants.OIConstants.kDriveDeadband),
          MathUtil.applyDeadband(-m_driveController.getZ()*Constants.Drivetrain.kTurningScale, Constants.OIConstants.kDriveDeadband))
      , m_drivetrain)
    );

    SmartDashboard.putData(m_drivetrain);

    
    //set up arm manual and auto functions
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}