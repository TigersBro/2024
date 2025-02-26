// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS5Controller;
import frc.robot.commands.cannon3;
import frc.robot.commands.cannon4;
import frc.robot.commands.CannonDown;
import frc.robot.commands.CannonUp;
import frc.robot.commands.Drive;
import frc.robot.commands.cannon3;
import frc.robot.commands.cannon4;
import frc.robot.commands.cannon5;
import frc.robot.commands.cannonAll;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.commands.Compressor_bro;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...



  /*  TODO  */
/*  We need to add buttons and remap buttons
 *  The operation of the robot should be:
 * 1) Flight controller only controls the driving of the robot.  We need to remove
 *    any of the buttons which have those controls mapped.
 * 2) The raising, lowering and firing of the robot should be controlled by the ps5 controller 
 *  
 * Button logic: 
 * left or right stick on the ps5 should control up/down of the lifter.
 * fire logic should use the same double trigger logic.  
 * R1 or L1  should 'unlock' the firing options (maybe a lock attribute in the Pneumatics class
 * that is set when you hold R1 and unset when you let go or if a cannon fires? )
 * DPAD left up and right should fire the cannons if unlocked
 * 
 */

  private final Joystick j_joy = new Joystick(Constants.Controls.JOYSTICK_USB);
  private final PS5Controller ps_5 = new PS5Controller(1);
  private final Pneumatics m_pneumatics = new Pneumatics();
  private final cannon3 c_fire3 = new cannon3(m_pneumatics);
  private final cannon4 c_fire4 = new cannon4(m_pneumatics);
  private final cannon5 c_fire5 = new cannon5(m_pneumatics);
  private final cannonAll c_fireAll = new cannonAll(m_pneumatics);
  private final CannonUp c_up = new CannonUp(m_pneumatics);
  private final CannonDown c_dn = new CannonDown(m_pneumatics);
  private final Compressor_bro c_compressor = new Compressor_bro(m_pneumatics);
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    
    configureButtonBindings();
   // m_pneumatics.enableCompressor();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    
   final JoystickButton b_up = new JoystickButton(ps_5, Constants.Controls.JOYSTICK_UP);
   final JoystickButton b_dn = new JoystickButton(ps_5, Constants.Controls.JOYSTICK_DN);
   final POVButton fire_3 = new POVButton(ps_5, Constants.Controls.JOYSTICK_FIRE3);
   final POVButton fire_4 = new POVButton(ps_5, Constants.Controls.JOYSTICK_FIRE4);
   final POVButton fire_5 = new POVButton(ps_5, Constants.Controls.JOYSTICK_FIRE5);
   final JoystickButton R_trigger = new JoystickButton(ps_5,6);
   final JoystickButton L_trigger = new JoystickButton(ps_5, 5);


   final JoystickButton b_Compressor = new JoystickButton(j_joy, Constants.Controls.STOP_COMPRESSOR);
   final DriveTrain m_driveTrain = new DriveTrain();
   final Drive drive = new Drive(m_driveTrain, j_joy);
  

    
    
    
    
    
    //Fire away
    fire_3.whileTrue(c_fire3);
    fire_4.whileTrue(c_fire4);
    fire_5.whileTrue(c_fire5);
    
    
    b_up.whileTrue(c_up);
    b_dn.whileTrue(c_dn);
    
    b_Compressor.whileTrue(c_compressor);
    m_driveTrain.setDefaultCommand(drive);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 
}
