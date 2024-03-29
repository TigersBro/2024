// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
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
  private final Lift m_lift = new Lift();

  private final Joystick m_driveController = new Joystick(Constants.OIConstants.kDriverController);
  private final PS5Controller m_ps5 = new PS5Controller(Constants.OIConstants.kOtherController);
  private final Command m_autonomousCommand = new AutonomousCommand(m_drivetrain, m_shooter, m_intake, m_ps5);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    CameraServer.startAutomaticCapture(0);

    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_drivetrain, m_shooter, m_intake, m_ps5));
    SmartDashboard.putData("Shoot Amp", new StartLaunch(m_shooter, m_ps5).withTimeout(Constants.Limits.shooterTimeout));

    SmartDashboard.putData(m_drivetrain);
    SmartDashboard.putData(m_intake);
    SmartDashboard.putData(m_shooter);

    // Configure the button bindings
    configureButtonBindings();
    // ps5 button mapping:
    // /** Square button. */
    // kSquare(1),
    // /** X button. */
    // kCross(2),
    // /** Circle button. */
    // kCircle(3),
    // /** Triangle button. */
    // kTriangle(4),
    // /** Left trigger 1 button. */
    // kL1(5),
    // /** Right trigger 1 button. */
    // kR1(6),
    // /** Left trigger 2 button. */
    // kL2(7),
    // /** Right trigger 2 button. */
    // kR2(8),
    // /** Create button. */
    // kCreate(9),
    // /** Options button. */
    // kOptions(10),
    // /** Left stick button. */
    // kL3(11),
    // /** Right stick button. */
    // kR3(12),
    // /** PlayStation button. */
    // kPS(13),
    // /** Touchpad click button. */
    // kTouchpad(14);

  }

  private void configureButtonBindings() {
    // set up the drivetrain command that runs all the time

    final JoystickButton shoot = new JoystickButton(m_driveController, 1); //trigger
    final JoystickButton makeFrontBack = new JoystickButton(m_driveController, 5);  //top left thumb
    final JoystickButton backupLiftButton = new JoystickButton(m_driveController, 4);
    final JoystickButton backupDeliveryButton = new JoystickButton(m_driveController, 6);

    final JoystickButton stopIt = new JoystickButton(m_ps5, 2); //X - Panic button
    final JoystickButton shootPS5Button = new JoystickButton(m_ps5, 8); //Right L2 Trigger
    final JoystickButton backupShootPS5Button = new JoystickButton(m_ps5, 7);  //Left L2 Trigger
    final JoystickButton backupIntakePS5Button = new JoystickButton(m_ps5, 3);  //Circle
    final JoystickButton shootLow = new JoystickButton(m_ps5, 4);  //Triangle
    final POVButton deliverHooks = new POVButton(m_ps5, 0); //ps5 DPAD up
    final POVButton liftEdD = new POVButton(m_ps5, 180); //ps5 DPAD down
    


    
    m_drivetrain.setDefaultCommand(new RunCommand(
        () -> m_drivetrain.arcadeDrive(m_driveController.getY(), m_driveController.getZ()), m_drivetrain));
    m_intake.setDefaultCommand( new RunCommand( 
        () -> m_intake.suck(m_ps5.getSquareButton()), m_intake));
    

    // Joystick Mappings
    shoot.onTrue(new ShootSpeakerSequence(m_shooter,m_intake,m_ps5));
    
    backupLiftButton.onTrue(new BackupLift( m_lift, m_ps5 ));
    backupDeliveryButton.onTrue(new BackupDelivery(m_lift, m_ps5));
    makeFrontBack.onTrue(new MakeFrontBack(m_drivetrain));
    //PS5 mappings
    stopIt.onTrue(new StopIntakeAndShooter(m_shooter, m_intake));
    shootPS5Button.onTrue(new ShootSpeakerSequence(m_shooter,m_intake,m_ps5));
    backupShootPS5Button.onTrue(new BackupShoot(m_shooter, m_ps5).withTimeout((3)));  //Shouldn't hit the timeout...but if we do...we do.
    backupIntakePS5Button.onTrue(new BackupFeed(m_intake, m_ps5).withTimeout(3));
    
    
    deliverHooks.onTrue(new StartDeliverHooks(m_lift, m_ps5).withTimeout(2));
    liftEdD.onTrue(new StartLift(m_lift, m_ps5));
    shootLow.onTrue( new ShootAmpSequence(m_shooter, m_intake, m_ps5));


    //How to put stuff to SmartDashboard.
    // SmartDashboard.putNumber("before", rot);

    
    



  }


  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}