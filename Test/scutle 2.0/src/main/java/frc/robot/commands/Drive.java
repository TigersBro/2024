// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class Drive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_drivetrain;
  private final SlewRateLimiter m_turnFilter ;
  private final SlewRateLimiter m_speedFilter ;
  private Joystick j_joy;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drive(DriveTrain dt, Joystick j) {
    m_drivetrain = dt;
    j_joy = j;
    m_speedFilter = new SlewRateLimiter(0.5);
    m_turnFilter = new SlewRateLimiter(.5);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     
    m_drivetrain.arcadeDrive(
                             j_joy.getY(), 
                             j_joy.getZ());

    // m_drivetrain.arcadeDrive(m_speedFilter.calculate(j_joy.getX()), 
    //                          j_joy.getY(), 
    //                          m_turnFilter.calculate(j_joy.getZ()));
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
