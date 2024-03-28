// RobotBuilder Version: 6.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
import frc.robot.Constants;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

///import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Lift extends SubsystemBase {

    //private PWMSparkMax shooterMotor;
    private VictorSP liftMotor1;
    private VictorSP liftMotor2;
    private PWMVictorSPX deliverHooks;
    private boolean m_hooksDelivered;

    public Lift() {
        //shooterMotor = new PWMSparkMax(7);
        liftMotor1 = new VictorSP(2);
        liftMotor2 = new VictorSP(3);
        deliverHooks = new PWMVictorSPX(4);
        
        liftMotor1.addFollower(liftMotor2);
        m_hooksDelivered = false;
    }

    @Override
    public void periodic() 
    {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void Stop() 
    {
        liftMotor1.set(0);
        liftMotor1.stopMotor();
        deliverHooks.stopMotor();
    
    }

    public void Deliver()
    {
        deliverHooks.set(.3);
        m_hooksDelivered = true;
    }

    public void ReverseDeliver()
    {
        deliverHooks.set(-.3);
        
    }


    public void LiftIt()
    {
        if( m_hooksDelivered == true)
        {
            liftMotor1.set(.3);
        }
        

    }
}
