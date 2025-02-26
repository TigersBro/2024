// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class Drive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_zRotation;
  private final DriveTrain m_drive;
  private final BooleanSupplier m_squared;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
 public Drive(DriveTrain driveSubsystem,DoubleSupplier xSpeed, DoubleSupplier zRotation, BooleanSupplier squareInputs)
  {
    m_xSpeed =  zRotation; 
    m_zRotation = xSpeed;
    m_drive = driveSubsystem;
    m_squared = squareInputs;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double modifier;
    modifier = m_xSpeed.getAsDouble() * -1;
    m_drive.driveArcade(modifier, m_zRotation.getAsDouble(), m_squared.getAsBoolean());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
