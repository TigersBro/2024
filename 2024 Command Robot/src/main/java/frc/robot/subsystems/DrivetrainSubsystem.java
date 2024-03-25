// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
//import frc.robot.Constants.OIConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.ADIS16470_IMU;

public class DrivetrainSubsystem extends SubsystemBase {
  // creating these here versus in the Drivtrain constructor and as final so it
  // cannot be changed.
  private CANSparkMax m_frontLeftMotor ;
  private  CANSparkMax m_frontRightMotor;
  private  CANSparkMax m_rearLeftMotor ;
  private  CANSparkMax m_rearRightMotor;
  private  DifferentialDrive m_drive; 
  private  Encoder m_encoderLeft;
  private  Encoder m_encoderRight;
  private  int m_makeFrontBack = 1;
  //private final ADIS16470_IMU m_gyro = new ADIS16470_IMU();
  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    // Only make calls you need to. This cuts down on having to read the code.
    
    m_frontLeftMotor = new CANSparkMax(Constants.motors.kFrontLeftCanId, CANSparkLowLevel.MotorType.kBrushed);
    m_frontRightMotor = new CANSparkMax(Constants.motors.kFrontRightCanId,CANSparkLowLevel.MotorType.kBrushed);
    m_rearLeftMotor = new CANSparkMax(Constants.motors.kRearLeftCanId,CANSparkLowLevel.MotorType.kBrushed);
    m_rearRightMotor = new CANSparkMax(Constants.motors.kRearRightCanId, CANSparkLowLevel.MotorType.kBrushed);
    m_drive = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);

    m_frontRightMotor.setInverted(Constants.Drivetrain.kFrontRightInverted);

    m_rearRightMotor.follow(m_frontRightMotor);
    m_rearLeftMotor.follow(m_frontLeftMotor);

    m_encoderLeft = new Encoder(0, 1, false);
    m_encoderLeft.setDistancePerPulse(Constants.Drivetrain.kEncoderDistancePerPulse);

    m_encoderRight = new Encoder(2, 3, true);
    m_encoderRight.setDistancePerPulse(Constants.Drivetrain.kEncoderDistancePerPulse);

    addChild("Quadrature Encoder Left", m_encoderLeft);
    addChild("Quadrature Encoder Right", m_encoderRight);

  }

 // public void driveArcade(double _straight, double _turn) {
    //double straight = MathUtil.clamp(_straight + _turn, -1.0, 1.0);
    //double turn = MathUtil.clamp(_straight - _turn, -1.0, 1.0);

  //  m_drive.arcadeDrive(_straight, _turn);
 // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    // builder.addDoubleProperty("Setpoint", () -> m_setpoint, (val) -> m_setpoint =
    // val);
    // builder.addBooleanProperty("At Setpoint", () -> atSetpoint(), null);
    // addChild("Controller", m_controller);
  }

  public void reset() {
    m_encoderLeft.reset();
    m_encoderRight.reset();

  }

  public double getDistance() {

    return (m_encoderLeft.getDistance() + m_encoderRight.getDistance() / 2);
  }

  public void log() {
    SmartDashboard.putNumber("Left Distance", m_encoderLeft.getDistance());
    SmartDashboard.putNumber("Right Distance", m_encoderRight.getDistance());
    SmartDashboard.putNumber("Left Speed", m_encoderLeft.getRate());
    SmartDashboard.putNumber("Right Speed", m_encoderRight.getRate());
  }
/////////////////////////////////////////commment/////////////////////////////////////////
  ///////////////////////////////////////////////////// comment in when we have a
  ///////////////////////////////////////////////////// rangefinder:///////////////////////////////////////////

  // public double getDistanceToObstacle() {
  // // Really meters in simulation since it's a rangefinder...
  // return m_rangefinder.getAverageVoltage();
  // }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
 //   m_gyro.reset();
  }

  public double getHeading() {
   // return Math.IEEEremainder(m_gyro.getAngle(), 360) * (OIConstants.kGyroReversed ? -1.0 : 1.0);
  
  return 0 ;}

  public double getTurnRate() {
    //return m_gyro.getRate() * (OIConstants.kGyroReversed ? -1.0 : 1.0);
    return 0 ;
  }

  public void arcadeDrive(double fwd, double rot) {

    
    rot = rot /2;
    fwd = fwd * m_makeFrontBack;
    rot = rot * m_makeFrontBack;
    m_drive.arcadeDrive(fwd, rot);
  }


  public void setFrontBack( )
  {
    if( m_makeFrontBack == -1 )
    {
      m_makeFrontBack = 1;
    }
    else
    {
      m_makeFrontBack = -1;
    }
 }

}
