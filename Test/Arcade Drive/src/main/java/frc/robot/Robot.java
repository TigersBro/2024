// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;



/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  
  private static final int leftFrontDeviceID = 5;
  private static final int leftRearDeviceID = 2;
  private static final int rightFrontDeviceID = 4;
  private static final int rightRearDeviceID = 3;
  private CANSparkMax m_leftFrontMotor;
  private CANSparkMax m_rightFrontMotor;
  private CANSparkMax m_leftRearMotor;
  private CANSparkMax m_rightRearMotor;
  private RelativeEncoder m_leftEncoder;
  private RelativeEncoder m_rightEncoder;
  
  private DifferentialDrive m_robotDrive;
      
  private final Joystick m_joy = new Joystick(0);

  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftFrontMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightFrontMotor);
    SendableRegistry.addChild(m_robotDrive, m_leftRearMotor);
    SendableRegistry.addChild(m_robotDrive, m_rightRearMotor);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_leftFrontMotor = new CANSparkMax(leftFrontDeviceID, MotorType.kBrushed);
    m_leftRearMotor = new CANSparkMax(leftRearDeviceID, MotorType.kBrushed);
    m_rightFrontMotor = new CANSparkMax(rightFrontDeviceID, MotorType.kBrushed);
    m_rightRearMotor = new CANSparkMax(rightRearDeviceID, MotorType.kBrushed);
    
    m_robotDrive = new DifferentialDrive(m_leftFrontMotor::set, m_rightFrontMotor::set);
  
    m_rightFrontMotor.setInverted(true);
    m_rightRearMotor.follow(m_rightFrontMotor);
    m_leftRearMotor.follow(m_leftFrontMotor);
    //m_leftEncoder = m_leftFrontMotor.getEncoder();
    //m_rightEncoder = m_rightFrontMotor.getEncoder();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_joy.getY(), -m_joy.getZ());
    
   // SmartDashboard.putNumber("Encoder Velocity", m_leftEncoder.getVelocity());
   // SmartDashboard.putNumber("Encoder Velocity", m_rightEncoder.getVelocity());
    

  }
}
