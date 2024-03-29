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
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class PositionAmp extends ArmSetpoint {
    public PositionAmp(Arm subsystem, PS5Controller controller) 
    {
        super(subsystem, Constants.armConstants.armAmpPos, controller);
    }
}
