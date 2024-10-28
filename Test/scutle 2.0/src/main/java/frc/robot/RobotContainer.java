// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Cannon;
import frc.robot.commands.CannonDown;
import frc.robot.commands.CannonUp;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Compressor_bro;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick j_joy = new Joystick(Constants.Controls.JOYSTICK_USB);
  private final Pneumatics m_pneumatics = new Pneumatics();
  private final Cannon c_fire = new Cannon(m_pneumatics);
  private final CannonUp c_up = new CannonUp(m_pneumatics);
  private final CannonDown c_dn = new CannonDown(m_pneumatics);
  private final Compressor_bro c_compressor = new Compressor_bro(m_pneumatics);
  private final JoystickButton b_shoot = new JoystickButton(j_joy, Constants.Controls.JOYSTICK_FIRE);
  private final JoystickButton b_up = new JoystickButton(j_joy, Constants.Controls.JOYSTICK_UP);
  private final JoystickButton b_dn = new JoystickButton(j_joy, Constants.Controls.JOYSTICK_DN);
  private final JoystickButton b_Compressor = new JoystickButton(j_joy, Constants.Controls.STOP_COMPRESSOR);
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Drive drive = new Drive(m_driveTrain, j_joy);

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
    b_shoot.whileTrue(c_fire);
    b_up.whileTrue(c_up);
    b_dn.whileTrue(c_dn);
    b_Compressor.onTrue(c_compressor);
    m_driveTrain.setDefaultCommand(drive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 
}
