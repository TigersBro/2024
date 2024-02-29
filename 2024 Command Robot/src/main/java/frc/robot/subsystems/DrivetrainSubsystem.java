// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;


import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;


public class DrivetrainSubsystem extends SubsystemBase {
  private CANSparkMax m_frontLeftMotor;
  private CANSparkMax m_frontRightMotor;
  private CANSparkMax m_rearLeftMotor;
  private CANSparkMax m_rearRightMotor;

private Encoder quadratureEncoderLeft;
private Encoder quadratureEncoderRight;



  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    m_frontLeftMotor  = new CANSparkMax(Constants.Drivetrain.kFrontLeftCanId, CANSparkLowLevel.MotorType.kBrushed);
    //m_frontLeftMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    m_frontLeftMotor.setInverted(Constants.Drivetrain.kFrontLeftInverted);
    //m_frontLeftMotor.burnFlash();
   


    m_frontRightMotor = new CANSparkMax(Constants.Drivetrain.kFrontRightCanId, CANSparkLowLevel.MotorType.kBrushed);
    m_frontRightMotor.setInverted(Constants.Drivetrain.kFrontRightInverted);
   // in constants, the front right motor's inversion is TRUE
   // m_frontRightMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
   // m_frontRightMotor.setIdleMode(IdleMode.kBrake);
   // m_frontRightMotor.burnFlash();
   

    m_rearLeftMotor   = new CANSparkMax(Constants.Drivetrain.kRearLeftCanId, CANSparkLowLevel.MotorType.kBrushed);
    m_rearLeftMotor.setInverted(Constants.Drivetrain.kRearLeftInverted);
    // in constants, the rear left motor's inversion is FALSE
   // m_rearLeftMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
   // m_rearLeftMotor.setIdleMode(IdleMode.kBrake);
   //m_rearLeftMotor.burnFlash();
   
    m_rearRightMotor  = new CANSparkMax(Constants.Drivetrain.kRearRightCanId, CANSparkLowLevel.MotorType.kBrushed);
    m_rearRightMotor.setInverted(Constants.Drivetrain.kRearRightInverted);
   // m_rearRightMotor.setSmartCurrentLimit(Constants.Drivetrain.kCurrentLimit);
    //m_rearRightMotor.setIdleMode(IdleMode.kBrake);
   // m_rearRightMotor.burnFlash();
   

    m_rearRightMotor.follow(m_frontRightMotor);
    m_rearLeftMotor.follow(m_frontLeftMotor);

   quadratureEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
   addChild("Quadrature Encoder Left",quadratureEncoderLeft);
   quadratureEncoderLeft.setDistancePerPulse(1.0);
 
   quadratureEncoderRight = new Encoder(2, 3, true, EncodingType.k4X);
   addChild("Quadrature Encoder Right",quadratureEncoderRight);
   quadratureEncoderRight.setDistancePerPulse(1.0);



  }

  public void driveArcade(double _straight, double _turn) {
    double left  = MathUtil.clamp(_straight + _turn, -1.0, 1.0);
    double right = MathUtil.clamp(_straight - _turn, -1.0, 1.0);

    m_frontLeftMotor.set(left);
    m_frontRightMotor.set(right);
    m_rearLeftMotor.set(left);
    m_rearRightMotor.set(right);
  }

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
    //builder.addDoubleProperty("Setpoint", () -> m_setpoint, (val) -> m_setpoint = val);
    //builder.addBooleanProperty("At Setpoint", () -> atSetpoint(), null);
    //addChild("Controller", m_controller);
  }
}