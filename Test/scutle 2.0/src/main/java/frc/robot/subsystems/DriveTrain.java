// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private Victor DT_L1;
  private Victor DT_L2;
  private Victor DT_R1;
  private Victor DT_R2;
  private DifferentialDrive drive;
  private boolean reverseIt;

  public DriveTrain() {
    DT_L1 = new Victor(Constants.Drivetrain.PWM_L1);
    DT_L2 = new Victor(Constants.Drivetrain.PWM_L2);
    DT_R1 = new Victor(Constants.Drivetrain.PWM_R1);
    DT_R2 = new Victor(Constants.Drivetrain.PWM_R2);
    DT_L1.addFollower(DT_L2);
    DT_R1.addFollower(DT_R2);
    reverseIt = false;
    drive = new DifferentialDrive(DT_L1, DT_R1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void driveArcade(double xSpeed, double zRotation, boolean squared) {
    
    
    ///I have no idea why these are reversed..but they are...
    //xspeed is actually rotation...
    //zrotation is actually xspeed
    if (reverseIt == true)
      xSpeed = xSpeed * -1;
      
    drive.arcadeDrive(xSpeed, zRotation, squared);
  }

  /**
   * Use this to drive the robot, with one stick controlling one 
   * side of the drivetrain and the other stick controlling the other.
   * 
   * @param leftSpeed speed to drive the left side of the robot at
   * @param rightSpeed speed to drive the right side of the robot at
   * @param squared do you square the inputs from the controller 
   */
  public void driveTank(double leftSpeed, double rightSpeed, boolean squared){
    drive.tankDrive(leftSpeed, rightSpeed, squared);
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void reverseIt(){
    if( reverseIt == true)
      reverseIt = false;
    else
      reverseIt = true; 
  } 
}
