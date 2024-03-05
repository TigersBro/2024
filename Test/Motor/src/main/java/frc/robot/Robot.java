// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This sample program shows how to control a motor using a joystick. In the operator control part
 * of the program, the joystick is read and the value is written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and motor controller inputs also range from -1 to 1
 * making it easy to work together.
 *
 * <p>In addition, the encoder value of an encoder connected to ports 0 and 1 is consistently sent
 * to the Dashboard.
 */
public class Robot extends TimedRobot {
  private static final int kMotorPort = 6;
  private static final int kJoystickPort = 0;

  private PWMSparkMax m_motor;
  private Joystick m_joystick;

  @Override
  public void robotInit() {
    m_motor = new PWMSparkMax(kMotorPort);
    m_joystick = new Joystick(kJoystickPort);
 }

  /*
   * The RobotPeriodic function is called every control packet no matter the
   * robot mode.
   */

  @Override
  public void teleopPeriodic() {
///////This works////////
    //    m_motor.set(m_joystick.getY());

    
    if ( m_joystick.getRawButtonPressed(6))
    {
      m_motor.set(1);

    }

    if (m_joystick.getRawButtonPressed(7))
    {
      m_motor.set(-1);
    }

    if (m_joystick.getRawButtonPressed(9))
    {
      m_motor.stopMotor();
    }

    if(m_joystick.getRawButtonReleased(6))
    {
      m_motor.stopMotor();
      
    }
   if(m_joystick.getRawButtonReleased(7))
    {
      m_motor.stopMotor();
      
    }


  }
}
