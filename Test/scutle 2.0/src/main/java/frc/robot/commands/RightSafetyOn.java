// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;
public class RightSafetyOn extends Command {
  private final Pneumatics m_pneumatics;
  /** Creates a new safetyOff. */
  public RightSafetyOn(Pneumatics pn) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_pneumatics = pn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_pneumatics.setRightSafetyStateOn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   //m_pneumatics.setLeftSafetyStateOff( );
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
