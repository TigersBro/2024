// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;


import frc.robot.subsystems.Intake;

    
public class BackupFeed extends Command {

        private  Intake m_intake;
        @SuppressWarnings("unused") 
        //not sure why this keeps giving warnings....
        private  PS5Controller m_control;
        private  boolean m_done;


    public BackupFeed (Intake subsystem, PS5Controller controller) 
    {
        m_intake = subsystem;
        m_control = controller;
        addRequirements(m_intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
        m_intake.reversesuck();
            
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.stop();

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        
        return m_done || m_control.getCrossButtonReleased()|| m_control.getCreateButtonReleased();
    }
    
}
