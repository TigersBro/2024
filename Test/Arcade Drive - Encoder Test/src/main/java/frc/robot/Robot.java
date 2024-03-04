// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

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
  private DifferentialDrive m_robotDrive;
  private Encoder m_encoder;
   private AnalogPotentiometer 

      
  m_pot = new AnalogPotentiometer(0, 1);


  //REV encoder
  private RelativeEncoder m_leftEncoder;
  //private RelativeEncoder m_rightEncoder;
  
  // FRC Encoder logic
  private static final int kEncoderPortA = 0;
  private static final int kEncoderPortB = 1;
      
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
  
    CameraServer.startAutomaticCapture();
  
    m_leftFrontMotor = new CANSparkMax(leftFrontDeviceID, MotorType.kBrushed);
    m_leftRearMotor = new CANSparkMax(leftRearDeviceID, MotorType.kBrushed);
    m_rightFrontMotor = new CANSparkMax(rightFrontDeviceID, MotorType.kBrushed);
    m_rightRearMotor = new CANSparkMax(rightRearDeviceID, MotorType.kBrushed);
    
    m_robotDrive = new DifferentialDrive(m_leftFrontMotor::set, m_rightFrontMotor::set);
  
    m_rightFrontMotor.setInverted(true);
    m_rightRearMotor.follow(m_rightFrontMotor);
    m_leftRearMotor.follow(m_leftFrontMotor);

    
    m_encoder = new Encoder(kEncoderPortA, kEncoderPortB);
    //m_leftEncoder = new RelativeEncoder();
    
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_joy.getY(), -m_joy.getZ());
    
    SmartDashboard.putNumber("Encoder", m_encoder.getDistance());
    //SmartDashboard.putNumber("Encoder Velocity", m_encoder.get());
    SmartDashboard.putNumber("Encoder Velocity",m_encoder.getRate() );
    SmartDashboard.putData(m_encoder);
    SmartDashboard.putNumber("pot", m_pot.get());
    
    //SmartDashboard.putNumber("Encoder Velocity", m_leftEncoder.getVelocity());

   // SmartDashboard.putNumber("Encoder Velocity", m_rightEncoder.getVelocity());
    

  }
}
