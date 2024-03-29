// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootSpeakerSequence extends SequentialCommandGroup {

  public ShootSpeakerSequence( Shooter shooter, Intake intake, PS5Controller controller) {
    addCommands
    (

    Commands.parallel
    (

        new BackupFeed(intake, controller).withTimeout(.2),
        new BackupShoot(shooter, controller).withTimeout(.2)
        
    ),
    new StartLaunch(shooter,controller).withTimeout(.4),

    Commands.parallel
    (
      
      new StartIntake(intake, controller).withTimeout(1),
      new StartLaunch(shooter,controller).withTimeout(1)

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
