// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.AutonomousCommand;
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
  private final XboxController m_otherController = new XboxController(Constants.OIConstants.kDriverController);
  
  private final Command m_autonomousCommand = new AutonomousCommand(m_drivetrain, m_arm, m_shooter, m_intake);
  
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_drivetrain, m_arm, m_shooter, m_intake));
    SmartDashboard.putData("Amp Pos", new PositionAmp( m_arm ));
    SmartDashboard.putData("Speaker Pos", new PositionSpeaker( m_arm ));
    SmartDashboard.putData("Intake Pos", new PositionIntake( m_arm ));
    
    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_arm);
    SmartDashboard.putData(m_intake);
    SmartDashboard.putData(m_shooter);

    // Configure the button bindings
    configureButtonBindings();
    

    
  }

  private void configureButtonBindings() {
    // set up the drivetrain command that runs all the time
    m_drivetrain.setDefaultCommand(new RunCommand(
        () -> m_drivetrain.driveArcade(m_driveController.getY(),-m_driveController.getZ()
          //  MathUtil.applyDeadband(m_driveController.getY(), Constants.OIConstants.kDriveDeadband),
            //MathUtil.applyDeadband(-m_driveController.getZ() * Constants.Drivetrain.kTurningScale, Constants.OIConstants.kDriveDeadband)
                ),
        m_drivetrain));


        final JoystickButton dpadUp = new JoystickButton(m_otherController, 5);
        final JoystickButton dpadDown = new JoystickButton(m_otherController, 7);
        dpadUp.toggleOnFalse( Commands.runOnce(() -> m_arm.Stop()));
        dpadUp.toggleOnTrue( Commands.runOnce(() -> m_arm.goUp(1)));
        dpadDown.toggleOnFalse( Commands.runOnce(() -> m_arm.Stop()));
        dpadDown.toggleOnTrue( Commands.runOnce(() -> m_arm.goUp(1)));

        // dpadDown.onTrue(new SetElevatorSetpoint(0.0, m_elevator));


  }


  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}