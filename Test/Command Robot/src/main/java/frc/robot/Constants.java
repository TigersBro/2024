// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */


public final class Constants {

  public static final class OIConstants {
    public static final int kDriverController = 0;
    public static final int kOtherController = 1;
    public static final double kDriveDeadband = 0.05;
    public static final double kArmManualDeadband = 0.05;
    public static final double kArmManualScale = 0.5;
}

 public static final class Drivetrain {
  public static final int kFrontLeftCanId = 5;
  public static final int kFrontRightCanId = 4;
  public static final int kRearLeftCanId = 2;
  public static final int kRearRightCanId = 3;

  public static final boolean kFrontLeftInverted = false;
  public static final boolean kFrontRightInverted = true;
  public static final boolean kRearLeftInverted = false;
  public static final boolean kRearRightInverted = false;

  public static final int kCurrentLimit = 55;

  public static final double kTurningScale = 0.5;

  public static final double kEncoderCPR = 2048; //https://www.revrobotics.com/rev-11-1271/
  public static final double kWheelDiameter = 8;
  // Circumference = diameter * pi. (inches) 
  public static final double kEncoderDistancePerPulse = (kWheelDiameter * Math.PI) / kEncoderCPR;

}

public static final class Scoring {
  //TODO set these to actual positions by reading the potentiometer when they are in position.
  public static final double ampPos = 1.1;
  public static final double speakerPos = .5;   
}
public static final class Limits{
  //TODO set these to actual positions by reading the potentiometer when they are in position.
  //scale 1...may need to play with this....
  public static final double armTop = 0.00096;
  public static final double armBottom = .0045; 


  public static final double armMaxSpeed = 1;
  
  public static final double shooterMaxSpeed = 1;
  public static final double shooterMinSpeed = .2;
  
  public static final double intakeMaxSpeed = 1;
  public static final double intakeminSpeed = .1;
  
  
}

public static final class armConstants{
  public static final double PVal = 4;
  public static final double IVal = 0.007;
  public static final double armTolerance = .0005;
  public static final double armAmpPos = .001;
  public static final double armIntakePos = .0045;
  public static final double armSpeakerPos = .0045;
  // public static final double armSetPoint 
}

public static final class driveStraight{
  public static final double PVal = 4;
  public static final double IVal = 0;

}
 
}



