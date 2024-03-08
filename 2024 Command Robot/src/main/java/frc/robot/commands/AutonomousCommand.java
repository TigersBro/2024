// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousCommand extends SequentialCommandGroup {

  public AutonomousCommand(DrivetrainSubsystem drive, Arm arm, Shooter shooter, Intake intake, PS5Controller controller) {
    addCommands
    (
        Commands.parallel
        (
          new DriveStraight(.1, drive),
          new PositionAmp(arm, controller)
        )
    );

        // new PrepareToPickup(claw, wrist, elevator),
        // new Pickup(claw, wrist, elevator),
        // new SetDistanceToBox(AutoConstants.kDistToBox1, drive),
        // // new DriveStraight(4), // Use encoders if ultrasonic is broken
        // new Place(claw, wrist, elevator),
        // new SetDistanceToBox(AutoConstants.kDistToBox2, drive),
        // // new DriveStraight(-2), // Use Encoders if ultrasonic is broken
        // Commands.parallel(
        //     new SetWristSetpoint(AutoConstants.kWristSetpoint, wrist), new CloseClaw(claw)));
  }
}
