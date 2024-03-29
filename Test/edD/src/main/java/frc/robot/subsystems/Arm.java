// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 *
 */
public class Arm extends SubsystemBase {

    private PWMSparkMax armMotor;
    private AnalogPotentiometer analogPotentiometer1;

    public Arm() {

        armMotor = new PWMSparkMax(6);
        addChild("Arm Motor", armMotor);
        armMotor.setInverted(false);

        analogPotentiometer1 = new AnalogPotentiometer(0, 1.0, 0.0);
        addChild("Analog Potentiometer 1", analogPotentiometer1);

    }

    public void log() {
        
        SmartDashboard.putData("Arm Pot", analogPotentiometer1);
    }

    @Override


    public void periodic() {
        // This method will be called once per scheduler run
        log();
    }

    @Override
    public void simulationPeriodic() {}
        // This method will be called once per scheduler run when in simulation

        

    public void goUp(){
        // TODO: Add limit switch and potentiometer
        //something like...
        // if analogPotentiometer1.get() == 1;
        armMotor.set(.5);



    }

    public void goDown(){
        // TODO: Add limit switch and potentiometer
        //something like...
        // if analogPotentiometer1.get() == Constant;
        
        armMotor.set(-.5);



    }




}
