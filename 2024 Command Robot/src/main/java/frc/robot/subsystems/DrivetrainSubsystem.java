// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends SubsystemBase {
  // creating these here versus in the Drivtrain constructor and as final so it
  // cannot be changed.
  private final CANSparkMax m_frontLeftMotor = new CANSparkMax(Constants.Drivetrain.kFrontLeftCanId,
      CANSparkLowLevel.MotorType.kBrushed);
  private final CANSparkMax m_frontRightMotor = new CANSparkMax(Constants.Drivetrain.kFrontRightCanId,
      CANSparkLowLevel.MotorType.kBrushed);
  private final CANSparkMax m_rearLeftMotor = new CANSparkMax(Constants.Drivetrain.kRearLeftCanId,
      CANSparkLowLevel.MotorType.kBrushed);
  private final CANSparkMax m_rearRightMotor = new CANSparkMax(Constants.Drivetrain.kRearRightCanId,
      CANSparkLowLevel.MotorType.kBrushed);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);
  private final Encoder m_quadratureEncoderLeft;
  private final Encoder m_quadratureEncoderRight;

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    // Only make calls you need to. This cuts down on having to read the code.
    m_frontRightMotor.setInverted(Constants.Drivetrain.kFrontRightInverted);

    m_rearRightMotor.follow(m_frontRightMotor);
    m_rearLeftMotor.follow(m_frontLeftMotor);

    m_quadratureEncoderLeft = new Encoder(0, 1, false);
    m_quadratureEncoderLeft.setDistancePerPulse(Constants.Drivetrain.kEncoderDistancePerPulse);

    m_quadratureEncoderRight = new Encoder(2, 3, true);
    m_quadratureEncoderRight.setDistancePerPulse(Constants.Drivetrain.kEncoderDistancePerPulse);

    addChild("Quadrature Encoder Left", m_quadratureEncoderLeft);
    addChild("Quadrature Encoder Right", m_quadratureEncoderRight);

  }

  public void driveArcade(double _straight, double _turn) {
    double straight = MathUtil.clamp(_straight + _turn, -1.0, 1.0);
    double turn = MathUtil.clamp(_straight - _turn, -1.0, 1.0);

    m_drive.arcadeDrive(straight, turn);
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
    // builder.addDoubleProperty("Setpoint", () -> m_setpoint, (val) -> m_setpoint =
    // val);
    // builder.addBooleanProperty("At Setpoint", () -> atSetpoint(), null);
    // addChild("Controller", m_controller);
  }
  

  public void reset() {
    m_quadratureEncoderLeft.reset();
    m_quadratureEncoderRight.reset();

  }
  public double getDistance(){

     return( m_quadratureEncoderLeft.getDistance() + m_quadratureEncoderRight.getDistance() / 2);
  }

  public void log() {
    SmartDashboard.putNumber("Left Distance", m_quadratureEncoderLeft.getDistance());
    SmartDashboard.putNumber("Right Distance", m_quadratureEncoderRight.getDistance());
    SmartDashboard.putNumber("Left Speed", m_quadratureEncoderLeft.getRate());
    SmartDashboard.putNumber("Right Speed", m_quadratureEncoderRight.getRate());
    // SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
  }


  /////////////////////////////////////////////////////comment in when we have a rangefinder:///////////////////////////////////////////

  // public double getDistanceToObstacle() {
  //   // Really meters in simulation since it's a rangefinder...
  //   return m_rangefinder.getAverageVoltage();
  // }


}