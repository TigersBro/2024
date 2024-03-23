// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.logging.Logger;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {


  private final Joystick m_joy = new Joystick(0);

  private final Victor m_leftDrive = new Victor(0);
  private final Victor m_rightDrive = new Victor(1);
  private final PWMVictorSPX x = new PWMVictorSPX(4);


  private final Victor m_leftDrive2 = new Victor(18);
  private final Victor m_rightDrive2 = new Victor(19);


  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftDrive, m_rightDrive);
  //private final PS5Controller m_controller = new PS5Controller(1);
  private final Timer m_timer = new Timer();

  StringLogEntry myStringLog;
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftDrive);
    SendableRegistry.addChild(m_robotDrive, m_rightDrive);
    // Starts recording to data log
    DataLogManager.start();


    // Record both DS control and joystick data
    DriverStation.startDataLog(DataLogManager.getLog());
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    
    DataLog log = DataLogManager.getLog();
    myStringLog = new StringLogEntry(log, "/my/string");
    m_rightDrive.setInverted(true);
    m_rightDrive.addFollower(m_rightDrive2);
    m_leftDrive.addFollower(m_leftDrive2);

    
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.2, 0.0, false);
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
//ps5
 //   m_robotDrive.arcadeDrive(m_driveController.getY(),-m_driveController.getZ());
// Joystick

//System.out.println("Another message");
    m_robotDrive.arcadeDrive(m_joy.getY(), -m_joy.getZ());
    
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
